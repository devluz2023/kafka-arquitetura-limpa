package br.com.faluz.service;

import br.com.faluz.app.dto.DeviceEventDTO;
import br.com.faluz.domain.entity.DeviceEvent;
import br.com.faluz.domain.usecase.IProcessDeviceEventUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeviceEventServiceTest {

    @Mock
    private IProcessDeviceEventUseCase processDeviceEventUseCase;

    @InjectMocks
    private DeviceEventService deviceEventService;

    @Test
    void processDeviceEvent_success() {
        DeviceEventDTO deviceEventDTO = new DeviceEventDTO("device123", true);
        DeviceEvent deviceEvent = new DeviceEvent(null, deviceEventDTO.device(), deviceEventDTO.released());

        deviceEventService.processDeviceEvent(deviceEventDTO);

        verify(processDeviceEventUseCase, times(1)).execute(deviceEvent);
    }

    @Test
    void processDeviceEvent_failure() {
        DeviceEventDTO deviceEventDTO = new DeviceEventDTO("device123", true);
        DeviceEvent deviceEvent = new DeviceEvent(null, deviceEventDTO.device(), deviceEventDTO.released());

        doThrow(new RuntimeException("Error")).when(processDeviceEventUseCase).execute(deviceEvent);

        assertThrows(RuntimeException.class, () -> deviceEventService.processDeviceEvent(deviceEventDTO));

        verify(processDeviceEventUseCase, times(1)).execute(deviceEvent);
    }
}