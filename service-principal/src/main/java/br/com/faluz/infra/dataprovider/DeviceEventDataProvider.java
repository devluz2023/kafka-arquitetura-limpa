package br.com.faluz.infra.dataprovider;

import br.com.faluz.domain.entity.Device;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DeviceEventDataProvider {

    public Device callExternalApi(Device request) {
        RestTemplate restTemplate = new RestTemplate();
        // Make the POST request and receive the response
        return restTemplate.postForObject("http://api.example.com/device/contracted",
                request, Device.class);
    }
}

