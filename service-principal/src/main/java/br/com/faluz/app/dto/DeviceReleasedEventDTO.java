package br.com.faluz.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record DeviceReleasedEventDTO(
        String device,
       LocalDate releaseDate,
        String clientName
) {
}
