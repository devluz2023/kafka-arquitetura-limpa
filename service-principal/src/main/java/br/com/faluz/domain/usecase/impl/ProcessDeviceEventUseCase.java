package br.com.faluz.domain.usecase.impl;


import br.com.faluz.domain.gateway.IDeviceEventGateway;
import br.com.faluz.domain.usecase.IProcessDeviceEventUseCase;
import br.com.faluz.infra.db.model.DeviceEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProcessDeviceEventUseCase implements IProcessDeviceEventUseCase {

    private final IDeviceEventGateway deviceEventGateway;


    @Override
    public void execute(DeviceEvent deviceEvent) {

    }
}
