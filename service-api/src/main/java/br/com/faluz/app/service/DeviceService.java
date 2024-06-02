package br.com.faluz.app.service;

import br.com.faluz.app.dto.DeviceContractedDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeviceService {

    public void createContractedDevice(DeviceContractedDTO deviceContractedDTO) throws Exception {
        // Mock processing logic
        log.info("Processing device: {}, released: {}", deviceContractedDTO.device(), deviceContractedDTO.released());

        // Simulate functional error
        if (deviceContractedDTO.device() == null || deviceContractedDTO.device().isEmpty()) {
            throw new IllegalArgumentException("Device field is mandatory");
        }

        // Additional logic can be added here
        // Simulate internal error
        if ("error".equals(deviceContractedDTO.device())) {
            throw new Exception("Simulated internal error");
        }
    }
}