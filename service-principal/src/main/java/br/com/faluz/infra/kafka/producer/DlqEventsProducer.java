package br.com.faluz.infra.kafka.producer;

import br.com.faluz.app.dto.DlqEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DlqEventsProducer {

    private static final String TOPIC_NAME = "devices.events.dlq";

//    @Autowired
//    private KafkaTemplate<String, DlqEventDTO> kafkaTemplate;
//
//    public void sendDlqEvent(DlqEventDTO event) {
//        kafkaTemplate.send(TOPIC_NAME, event);
//    }
}