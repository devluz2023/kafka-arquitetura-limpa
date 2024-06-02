package br.com.faluz.infra.kafka.consumer;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}