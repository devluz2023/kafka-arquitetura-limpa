package br.com.faluz.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DeviceReleasedEventDTO(
        @JsonProperty("device") String device,
        @JsonProperty("releaseDate") String releaseDate,
        @JsonProperty("clientName") String clientName
) {
}
