package br.com.faluz.service;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ExternalApiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ExternalApiService externalApiService;

    @Test
    void callExternalApi_success() {
        String event = "device123";
        String apiUrl = "http://external-api/endpoint";

        doNothing().when(restTemplate).postForObject(apiUrl, event, String.class);

        externalApiService.callExternalApi(event);

        verify(restTemplate, times(1)).postForObject(apiUrl, event, String.class);
    }

    @Test
    void callExternalApi_failure() {
        String event = "device123";
        String apiUrl = "http://external-api/endpoint";

        doThrow(new RuntimeException("API Error")).when(restTemplate).postForObject(apiUrl, event, String.class);

        assertThrows(RuntimeException.class, () -> externalApiService.callExternalApi(event));

        verify(restTemplate, times(1)).postForObject(apiUrl, event, String.class);
    }
}