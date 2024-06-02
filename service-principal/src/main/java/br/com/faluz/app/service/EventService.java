package br.com.faluz.app.service;

import br.com.faluz.app.dto.AccountCodeContractedEventDTO;
import br.com.faluz.app.dto.DeviceContractedEventDTO;
import br.com.faluz.cross.ZonedDateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EventService {

    private final ZonedDateFactory zonedDateFactory;

    @Autowired
    public EventService(ZonedDateFactory zonedDateFactory) {
        this.zonedDateFactory = zonedDateFactory;
    }

    public DeviceContractedEventDTO createDeviceContractedEvent(String device, LocalDate releaseDate, String clientName) {
        return new DeviceContractedEventDTO(device, releaseDate, clientName, zonedDateFactory.now());
    }

    public AccountCodeContractedEventDTO createAccountCodeContractedEvent(String accountcode, LocalDate releaseDate, String clientName) {
        return new AccountCodeContractedEventDTO(accountcode, releaseDate, clientName, zonedDateFactory.now());
    }
}
