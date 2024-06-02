package br.com.faluz.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public record AccountCodeContractedEventDTO(
        String accountcode,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate releaseDate,

        String clientName,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        ZonedDateTime eventTime
) {}
