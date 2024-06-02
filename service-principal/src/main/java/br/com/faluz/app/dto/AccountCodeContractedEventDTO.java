package br.com.faluz.app.dto;

import java.time.LocalDate;

public record AccountCodeContractedEventDTO(String device, LocalDate releaseDate,
                                            String clientName)
{
}
