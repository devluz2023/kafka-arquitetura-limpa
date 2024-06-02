package br.com.faluz.app.dto;

import java.time.LocalDate;

public record DeviceRequestDTO(
        Long id,
        String device,
        boolean releaseForUse,
        String clientName,
        LocalDate releaseDate
) {}
