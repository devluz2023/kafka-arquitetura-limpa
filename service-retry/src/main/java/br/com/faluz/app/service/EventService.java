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
        String formattedDate = zonedDateFactory.formatAndParseDate(releaseDate);
        LocalDate parsedDate = zonedDateFactory.parseFormattedDate(formattedDate);
        return new DeviceContractedEventDTO(device, parsedDate, clientName);
    }

    public AccountCodeContractedEventDTO createAccountCodeContractedEvent(String accountcode, LocalDate releaseDate, String clientName) {
        String formattedDate = zonedDateFactory.formatAndParseDate(releaseDate);
        LocalDate parsedDate = zonedDateFactory.parseFormattedDate(formattedDate);
        return new AccountCodeContractedEventDTO(accountcode, parsedDate, clientName);
    }
}
