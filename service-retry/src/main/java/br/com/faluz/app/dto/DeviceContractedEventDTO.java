package br.com.faluz.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public record DeviceContractedEventDTO(
        String device,


        LocalDate releaseDate,

        String clientName



) {}
