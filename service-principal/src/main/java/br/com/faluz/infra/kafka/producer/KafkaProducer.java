package br.com.faluz.infra.kafka.producer;

import br.com.faluz.app.dto.DeviceReleasedEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, DeviceReleasedEventDTO> kafkaTemplate;
    private static final String DEVICES_EVENTS_TOPIC = "devices.events";

    public void sendDeviceReleasedEvent(DeviceReleasedEventDTO deviceReleasedEventDTO) {
        kafkaTemplate.send(DEVICES_EVENTS_TOPIC, deviceReleasedEventDTO);
    }
}
