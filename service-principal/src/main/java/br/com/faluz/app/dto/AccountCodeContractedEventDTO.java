package br.com.faluz.app.dto;

import java.time.LocalDate;

public record AccountCodeContractedEventDTO(String accountCode, LocalDate releaseDate, String clientName) {
}
