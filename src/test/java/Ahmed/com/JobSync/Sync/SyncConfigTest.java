package Ahmed.com.JobSync.Sync;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringJUnitConfig.class)
class SyncConfigTest {

    @Test
    void syncConfig_ConstructorInjection_Successful() {
        String expectedApiHost = "jsearch.p.rapidapi.com";
        String expectedApiKey = "test-api-key-12345";
        String expectedBaseUrl = "https://jsearch.p.rapidapi.com/search";

        SyncConfig syncConfig = new SyncConfig(expectedApiHost, expectedApiKey, expectedBaseUrl);

        assertNotNull(syncConfig);
        assertEquals(expectedApiHost, syncConfig.getApiHost());
        assertEquals(expectedApiKey, syncConfig.getApiKey());
        assertEquals(expectedBaseUrl, syncConfig.getBaseUrl());
    }

    @Test
    void syncConfig_NullValues_HandlesGracefully() {
        SyncConfig syncConfig = new SyncConfig(null, null, null);

        assertNotNull(syncConfig);
        assertEquals(null, syncConfig.getApiHost());
        assertEquals(null, syncConfig.getApiKey());
        assertEquals(null, syncConfig.getBaseUrl());
    }

    @Test
    void syncConfig_EmptyValues_HandlesGracefully() {
        SyncConfig syncConfig = new SyncConfig("", "", "");

        assertNotNull(syncConfig);
        assertEquals("", syncConfig.getApiHost());
        assertEquals("", syncConfig.getApiKey());
        assertEquals("", syncConfig.getBaseUrl());
    }

    @Test
    void syncConfig_RealWorldValues() {
        String apiHost = "jsearch.p.rapidapi.com";
        String apiKey = "your-rapidapi-key-here";
        String baseUrl = "https://jsearch.p.rapidapi.com/search";

        SyncConfig syncConfig = new SyncConfig(apiHost, apiKey, baseUrl);

        assertNotNull(syncConfig);
        assertEquals(apiHost, syncConfig.getApiHost());
        assertEquals(apiKey, syncConfig.getApiKey());
        assertEquals(baseUrl, syncConfig.getBaseUrl());
    }

    @Test
    void syncConfig_InvalidBaseUrl_ThrowsException() {
        SyncConfig syncConfig = new SyncConfig("host", "key", "invalid-url");

        assertThrows(IllegalStateException.class, () -> {
            syncConfig.validateConfig();
        });
    }

    @Test
    void syncConfig_NullApiKey_ThrowsException() {
        SyncConfig syncConfig = new SyncConfig("host", null, "https://valid-url.com");

        assertThrows(IllegalStateException.class, () -> {
            syncConfig.validateConfig();
        });
    }

    @Test
    void syncConfig_EmptyApiKey_ThrowsException() {
        SyncConfig syncConfig = new SyncConfig("host", "", "https://valid-url.com");

        assertThrows(IllegalStateException.class, () -> {
            syncConfig.validateConfig();
        });
    }

    @Test
    void syncConfig_NullApiHost_ThrowsException() {
        SyncConfig syncConfig = new SyncConfig(null, "key", "https://valid-url.com");

        assertThrows(IllegalStateException.class, () -> {
            syncConfig.validateConfig();
        });
    }

    @Test
    void syncConfig_EmptyApiHost_ThrowsException() {
        SyncConfig syncConfig = new SyncConfig("", "key", "https://valid-url.com");

        assertThrows(IllegalStateException.class, () -> {
            syncConfig.validateConfig();
        });
    }
}
