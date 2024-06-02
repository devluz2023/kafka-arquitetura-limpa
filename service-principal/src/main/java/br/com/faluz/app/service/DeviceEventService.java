package br.com.faluz.app.service;

import br.com.faluz.app.dto.DeviceEventDTO;
import br.com.faluz.domain.entity.Device;
import br.com.faluz.infra.db.model.DeviceEvent;
import br.com.faluz.infra.db.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DeviceEventService {

    @Autowired
    private DeviceRepository deviceRepository;



    public void processDeviceEvent(DeviceEventDTO deviceEventDTO) {
    }

    public void updateDevice(DeviceEvent deviceEvent) {
    }
}