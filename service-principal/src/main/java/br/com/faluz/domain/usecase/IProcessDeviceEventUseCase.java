package br.com.faluz.domain.usecase;

import br.com.faluz.domain.entity.Device;

public interface IProcessDeviceEventUseCase {
    void execute(Device deviceEvent);
}
