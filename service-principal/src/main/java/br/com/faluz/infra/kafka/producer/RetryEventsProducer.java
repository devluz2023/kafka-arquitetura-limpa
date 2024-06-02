package br.com.faluz.infra.kafka.producer;

import br.com.faluz.app.dto.RetryEventDTO;
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
public class RetryEventsProducer {
    private static final String TOPIC_NAME = "devices.events.retry";
//    @Autowired
//    private KafkaTemplate<String, RetryEventDTO> kafkaTemplate;
//
//    public void sendRetryEvent(RetryEventDTO event) {
//        kafkaTemplate.send(TOPIC_NAME, event);
//    }
}
