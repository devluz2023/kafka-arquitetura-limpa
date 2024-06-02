package br.com.faluz.producer;


import br.com.faluz.infra.kafka.producer.DeviceProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.*;

class DeviceEventProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private DeviceProducer deviceEventProducer;

    @Test
    void sendRetryEvent() {
        String message = "device123";
        int attemptCounter = 1;

        ProducerRecord<String, String> record = new ProducerRecord<>("devices.events.retry", message);
        record.headers().add("attempt-counter", String.valueOf(attemptCounter).getBytes());

        deviceEventProducer.sendRetryEvent(message, attemptCounter);

        verify(kafkaTemplate, times(1)).send(record);
    }

    @Test
    void sendDlqEvent() {
        String message = "device123";
        Exception e = new Exception("Error");

        ProducerRecord<String, String> record = new ProducerRecord<>("devices.events.dlq", message);
        record.headers().add("errorEnum", "PROCESSING_ERROR".getBytes());
        record.headers().add("errorException", e.getMessage().getBytes());

        deviceEventProducer.sendDlqEvent(message, e);

        verify(kafkaTemplate, times(1)).send(record);
    }
}