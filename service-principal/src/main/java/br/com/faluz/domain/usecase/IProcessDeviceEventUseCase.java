package br.com.faluz.domain.usecase;


import br.com.faluz.infra.db.model.DeviceEvent;

public interface IProcessDeviceEventUseCase {
    void execute(DeviceEvent deviceEvent);
}
