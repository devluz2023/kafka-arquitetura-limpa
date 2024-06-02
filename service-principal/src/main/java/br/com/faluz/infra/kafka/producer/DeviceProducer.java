package br.com.faluz.infra.kafka.producer;

import br.com.faluz.infra.db.model.DeviceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeviceProducer {

    private final KafkaTemplate<String, DeviceEvent> kafkaTemplate;

    @Autowired
    public DeviceProducer(KafkaTemplate<String, DeviceEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(String topic, DeviceEvent deviceEvent) {
        kafkaTemplate.send(topic, deviceEvent);
    }
}
