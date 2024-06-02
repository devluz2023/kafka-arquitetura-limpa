package br.com.faluz.app.dto;

public record DLQEventDTO(String device, ErrorEnum errorEnum, String errorException) {
}