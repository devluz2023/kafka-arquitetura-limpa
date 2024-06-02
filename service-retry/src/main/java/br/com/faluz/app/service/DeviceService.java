package br.com.faluz.app.service;


import br.com.faluz.infra.db.model.DeviceEvent;
import br.com.faluz.infra.db.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public void updateDevice(DeviceEvent deviceEvent) {
        // Map DeviceEvent to Device entity and save to repository
    }
}