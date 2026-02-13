package Ahmed.com.JobSync.Sync;

import Ahmed.com.JobSync.Sync.dtos.JSearchResponseDto;
import Ahmed.com.JobSync.Sync.dtos.JobQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.util.ArrayList;


import org.springframework.web.util.UriComponentsBuilder ;

@Component
public class JSearchClient {

    private final SyncConfig syncConfig ;
    private final RestTemplate restTemplate ;
    private static final Logger log = LoggerFactory.getLogger(JSearchClient.class);

    public JSearchClient( SyncConfig syncConfig ,RestTemplate restTemplate ) {
        this.syncConfig = syncConfig;
        this.restTemplate = restTemplate;
    }

  public JSearchResponseDto fetchRawJobs(JobQuery query){
      // 1 : call urlBuilder (JobPreference preference) : return the url to sent
      URI uri = buildUri(query);
      // 2 : call headersBuilder() : return the headers
      HttpHeaders headers = headersBuilder();
      // 3:  call executeRequest(URI uri) : return the raw JSON
      // 4: return Raw Json job
      return executeRequest(uri , headers);
    }
    private URI buildUri(JobQuery query) {
        String title = (query.getTitle() != null) ? query.getTitle().trim() : "Software Engineer";
        String location = (query.getLocation() != null) ?  query.getLocation().trim() : "Remote";
        String fullQuery = title + " in " + location;

        URI uri = UriComponentsBuilder.fromUriString(syncConfig.getBaseUrl())
                .queryParam("query", fullQuery)
                .queryParam("page", "1")
                .queryParam("num_pages", "1")
                .queryParam("remote_jobs_only",  query.getIsRemote())
                .build()
                .encode()
                .toUri();
        log.debug("Targeting JSearch URI: {}", uri);
        return uri;
    }
    private JSearchResponseDto executeRequest(URI uri, HttpHeaders headers) {
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            // 1. Change String.class to JSearchResponseDto.class
            ResponseEntity<JSearchResponseDto> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    entity,
                    JSearchResponseDto.class
            );
            // 2. The "Happy Path"
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody();
            }
            // 3. Handle non-200 / Empty responses
            log.warn("API returned status: {} for URI: {}", response.getStatusCode(), uri);
            return createEmptyResponse("OK");

        } catch (Exception e) {
            // 4. The "Safety Net"
            log.error("JSearch API Failure for URI {}: {}", uri, e.getMessage());
            return createEmptyResponse("ERROR");
        }
    }
    private JSearchResponseDto createEmptyResponse(String status) {
        JSearchResponseDto empty = new JSearchResponseDto();
        empty.setStatus(status);
        empty.setData(new ArrayList<>()); // Return empty list so loops don't crash
        return empty;
    }
    private HttpHeaders headersBuilder(){
        HttpHeaders headers = new HttpHeaders();
        // Everything comes from the config now
        headers.set("X-RapidAPI-Key", syncConfig.getApiKey());
        headers.set("X-RapidAPI-Host", syncConfig.getApiHost());
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return headers ;
    }
}
