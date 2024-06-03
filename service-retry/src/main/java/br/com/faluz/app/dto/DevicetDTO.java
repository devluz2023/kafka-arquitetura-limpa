package br.com.faluz.app.dto;

import java.time.LocalDate;

public record DevicetDTO(Long id,
                         LocalDate releaseDate,
                         String device,
                         Boolean releaseForUse,
                          String clientName) {
}
