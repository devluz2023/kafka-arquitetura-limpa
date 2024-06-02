package br.com.faluz.app.service;

import br.com.faluz.infra.db.model.DeviceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

    @Autowired
    private RestTemplate restTemplate;

    public void callExternalApi(DeviceEvent deviceEvent) {
        String url = "http://localhost:8080/device/contracted";
        DeviceEventRequest request = new DeviceEventRequest(deviceEvent.getDevice(), true);
        restTemplate.postForObject(url, request, Void.class);
    }

    static class DeviceEventRequest {
        private String device;
        private Boolean released;

        public DeviceEventRequest(String device, Boolean released) {
            this.device = device;
            this.released = released;
        }

        // Getters and setters
    }
}