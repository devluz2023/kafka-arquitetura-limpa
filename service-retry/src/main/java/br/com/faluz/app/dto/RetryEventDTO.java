package br.com.faluz.app.dto;

public record RetryEventDTO(String device,
                            RetryErrorEnum errorEnum, String errorException) {
}