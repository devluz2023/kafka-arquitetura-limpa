package br.com.faluz.domain.usecase.impl;

import br.com.faluz.domain.entity.DeviceEvent;
import br.com.faluz.domain.gateway.IDeviceEventGateway;
import br.com.faluz.domain.usecase.IProcessDeviceEventUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProcessDeviceEventUseCase implements IProcessDeviceEventUseCase {

    private final IDeviceEventGateway deviceEventGateway;

    @Override
    public void execute(DeviceEvent deviceEvent) {
        // Save the device event
        deviceEventGateway.save(deviceEvent);

        // Call external API
        deviceEventGateway.callExternalApi(deviceEvent);
    }
}
