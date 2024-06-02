package br.com.faluz.domain.usecase.impl;

import br.com.faluz.domain.usecase.IDevice;
import br.com.faluz.infra.db.model.DeviceTable;
import br.com.faluz.infra.db.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceUseCase implements IDevice {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public void updateDevice(DeviceTable device) {
        deviceRepository.save(device);
    }
}
