package br.com.faluz.domain.usecase;

import br.com.faluz.domain.entity.DeviceEvent;

public interface IProcessDeviceEventUseCase {
    void execute(DeviceEvent deviceEvent);
}
