package br.com.faluz.app.dto;

import java.time.LocalDate;

public record DeviceContractedEventDTO(String device, LocalDate releaseDate, String clientName) {
}
