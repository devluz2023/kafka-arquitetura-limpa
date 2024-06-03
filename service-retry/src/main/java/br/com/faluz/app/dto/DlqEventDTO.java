package br.com.faluz.app.dto;

public record DlqEventDTO(String device,
                          DlqErrorEnum errorEnum, String errorException) {
}