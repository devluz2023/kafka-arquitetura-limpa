package br.com.faluz.app.dto;
public record RetryEventDTO(String device, ErrorEnum errorEnum, String errorException) {
}