package br.com.faluz.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public record AccountCodeContractedEventDTO(
        String accountcode,


        LocalDate releaseDate,

        String clientName



) {}
