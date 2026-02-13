package Ahmed.com.JobSync.Sync;

import Ahmed.com.JobSync.Sync.dtos.JSearchResponseDto;
import Ahmed.com.JobSync.Sync.dtos.JobQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JSearchClientTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private SyncConfig syncConfig;

    @InjectMocks
    private JSearchClient jSearchClient;

    private JobQuery jobQuery;
    private ResponseEntity<JSearchResponseDto> mockResponse;

    @BeforeEach
    void setUp() {
        jobQuery = new JobQuery();
        jobQuery.setTitle("Software Engineer");
        jobQuery.setLocation("Remote");
        jobQuery.setIsRemote(true);

        JSearchResponseDto responseDto = new JSearchResponseDto();
        responseDto.setStatus("OK");
        responseDto.setData(new ArrayList<>());
        mockResponse = new ResponseEntity<>(responseDto, HttpStatus.OK);

        when(syncConfig.getBaseUrl()).thenReturn("https://jsearch.p.rapidapi.com/search");
        when(syncConfig.getApiKey()).thenReturn("test-api-key");
        when(syncConfig.getApiHost()).thenReturn("jsearch.p.rapidapi.com");
    }

    @Test
    void fetchRawJobs_Successful() {
        when(restTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(JSearchResponseDto.class)))
                .thenReturn(mockResponse);

        JSearchResponseDto result = jSearchClient.fetchRawJobs(jobQuery);

        assertNotNull(result);
        assertEquals("OK", result.getStatus());
        assertNotNull(result.getData());

        verify(restTemplate).exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(JSearchResponseDto.class));
    }

    @Test
    void fetchRawJobs_RestClientException() {
        when(restTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(JSearchResponseDto.class)))
                .thenThrow(new RestClientException("API Error"));

        JSearchResponseDto result = jSearchClient.fetchRawJobs(jobQuery);

        assertNotNull(result);
        assertEquals("ERROR", result.getStatus());
        assertNotNull(result.getData());
        assertTrue(result.getData().isEmpty());

        verify(restTemplate).exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(JSearchResponseDto.class));
    }

    @Test
    void fetchRawJobs_NullQuery() {
        JobQuery nullQuery = new JobQuery();
        nullQuery.setIsRemote(false);

        when(restTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(JSearchResponseDto.class)))
                .thenReturn(mockResponse);

        JSearchResponseDto result = jSearchClient.fetchRawJobs(nullQuery);

        assertNotNull(result);
        assertEquals("OK", result.getStatus());
        verify(restTemplate).exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(JSearchResponseDto.class));
    }

    @Test
    void buildUri_WithValidQuery() {
        URI uri = (URI) ReflectionTestUtils.invokeMethod(jSearchClient, "buildUri", jobQuery);

        assertNotNull(uri);
        assertTrue(uri.toString().contains("query=Software+Engineer+in+Remote"));
        assertTrue(uri.toString().contains("remote_jobs_only=true"));
        assertTrue(uri.toString().contains("page=1"));
        assertTrue(uri.toString().contains("num_pages=1"));
    }

    @Test
    void buildUri_WithNullValues() {
        JobQuery nullQuery = new JobQuery();
        nullQuery.setIsRemote(false);

        URI uri = (URI) ReflectionTestUtils.invokeMethod(jSearchClient, "buildUri", nullQuery);

        assertNotNull(uri);
        assertTrue(uri.toString().contains("query=Software+Engineer+in+Remote"));
        assertTrue(uri.toString().contains("remote_jobs_only=false"));
    }

    @Test
    void headersBuilder_CreatesCorrectHeaders() {
        HttpHeaders headers = (HttpHeaders) ReflectionTestUtils.invokeMethod(jSearchClient, "headersBuilder");

        assertNotNull(headers);
        assertEquals("test-api-key", headers.get("X-RapidAPI-Key"));
        assertEquals("jsearch.p.rapidapi.com", headers.get("X-RapidAPI-Host"));
        assertEquals(MediaType.APPLICATION_JSON_VALUE, headers.get("Accept"));
    }

    @Test
    void executeRequest_Successful() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "test-api-key");
        headers.set("X-RapidAPI-Host", "jsearch.p.rapidapi.com");

        when(restTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(JSearchResponseDto.class)))
                .thenReturn(mockResponse);

        URI testUri = URI.create("https://example.com/test");
        JSearchResponseDto result = (JSearchResponseDto) ReflectionTestUtils.invokeMethod(jSearchClient, "executeRequest", testUri, headers);

        assertEquals("OK", result.getStatus());
        assertNotNull(result.getData());
        verify(restTemplate).exchange(testUri, HttpMethod.GET, new HttpEntity<>(headers), JSearchResponseDto.class);
    }

    @Test
    void executeRequest_Exception() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "test-api-key");
        headers.set("X-RapidAPI-Host", "jsearch.p.rapidapi.com");

        when(restTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(JSearchResponseDto.class)))
                .thenThrow(new RuntimeException("API Error"));

        URI testUri = URI.create("https://example.com/test");
        JSearchResponseDto result = (JSearchResponseDto) ReflectionTestUtils.invokeMethod(jSearchClient, "executeRequest", testUri, headers);

        assertEquals("ERROR", result.getStatus());
        assertNotNull(result.getData());
        assertTrue(result.getData().isEmpty());
        verify(restTemplate).exchange(testUri, HttpMethod.GET, new HttpEntity<>(headers), JSearchResponseDto.class);
    }

    @Test
    void createEmptyResponse_CreatesValidResponse() {
        JSearchResponseDto result = (JSearchResponseDto) ReflectionTestUtils.invokeMethod(jSearchClient, "createEmptyResponse", "OK");

        assertEquals("OK", result.getStatus());
        assertNotNull(result.getData());
        assertTrue(result.getData().isEmpty());
    }
}
