package br.com.faluz.domain.usecase.impl;

import br.com.faluz.domain.entity.Device;
import br.com.faluz.domain.gateway.IDeviceEventGateway;
import br.com.faluz.domain.usecase.IProcessDeviceEventUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProcessDeviceEventUseCase implements IProcessDeviceEventUseCase {

    private final IDeviceEventGateway deviceEventGateway;

    @Override
    public void execute(Device deviceEvent) {
        // Process the device event
        deviceEventGateway.callExternalApi(deviceEvent);
    }
}
