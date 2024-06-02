package br.com.faluz;

import br.com.faluz.app.dto.DeviceContractedDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeviceControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createContractedDevice_returnsCreatedStatus() {
        DeviceContractedDTO deviceContractedDTO = new DeviceContractedDTO("device123",
                true);

        ResponseEntity<Void> response =
                restTemplate.postForEntity("/device/contracted", deviceContractedDTO, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void createContractedDevice_withNullDevice_returnsBadRequestStatus() {
        DeviceContractedDTO deviceContractedDTO = new DeviceContractedDTO(null, true);

        ResponseEntity<Void> response = restTemplate.postForEntity("/device/contracted",
                deviceContractedDTO, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void createContractedDevice_withInternalErrorSimulation_returnsInternalServerErrorStatus() {
        DeviceContractedDTO deviceContractedDTO = new DeviceContractedDTO("error",
                true);

        ResponseEntity<Void> response = restTemplate.postForEntity("/device/contracted",
                deviceContractedDTO, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}