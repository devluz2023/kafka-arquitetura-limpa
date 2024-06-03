package br.com.faluz.infra.dataprovider;

import br.com.faluz.domain.entity.Device;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DeviceEventDataProvider {

    public ResponseEntity<Device> callExternalApi(Device request) {
        RestTemplate restTemplate = new RestTemplate();
        // Make the POST request and receive the response
        return restTemplate.postForEntity("http://localhost:8080/device/contracted",
                request, Device.class);
    }
}
