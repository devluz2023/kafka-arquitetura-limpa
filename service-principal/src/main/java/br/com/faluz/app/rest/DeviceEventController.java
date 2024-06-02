package br.com.faluz.app.rest;

import br.com.faluz.app.dto.DeviceEventDTO;
import br.com.faluz.app.service.DeviceEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device-event")
@RequiredArgsConstructor
public class DeviceEventController {

    private final DeviceEventService deviceEventService;

    @PostMapping("/process")
    public ResponseEntity<Void> processDeviceEvent(@RequestBody DeviceEventDTO deviceEventDTO) {
        deviceEventService.processDeviceEvent(deviceEventDTO);
        return ResponseEntity.ok().build();
    }
}