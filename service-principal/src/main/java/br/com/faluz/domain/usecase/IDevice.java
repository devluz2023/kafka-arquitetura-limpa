package br.com.faluz.domain.usecase;


import br.com.faluz.infra.db.model.DeviceTable;

public interface IDevice {

    void updateDevice(DeviceTable deviceEvent);
}
