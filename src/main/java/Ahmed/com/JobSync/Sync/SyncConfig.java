package Ahmed.com.JobSync.Sync;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SyncConfig {
    private final String apiHost;
    private final String apiKey;
    private final String baseUrl;

    // You must place the @Value annotations here for constructor injection
    public SyncConfig(
            @Value("${api.jsearch.host}") String apiHost,
            @Value("${api.jsearch.api-private-key}") String apiKey,
            @Value("${api.jsearch.base-url}") String baseUrl
    )
    {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.apiHost = apiHost ;
    }

    public String getApiKey() { return apiKey; }
    public String getBaseUrl() { return baseUrl; }
    public String getApiHost() {return apiHost;}

    @PostConstruct
    public void validateConfig() {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalStateException("API key cannot be null or empty");
        }
        if (apiHost == null || apiHost.trim().isEmpty()) {
            throw new IllegalStateException("API host cannot be null or empty");
        }
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            throw new IllegalStateException("Base URL cannot be null or empty");
        }
        if (!baseUrl.startsWith("http://") && !baseUrl.startsWith("https://")) {
            throw new IllegalStateException("Base URL must start with http:// or https://");
        }
    }
}