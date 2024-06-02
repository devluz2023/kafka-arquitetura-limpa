//package br.com.faluz.infra.kafka.producer;
//
//import br.com.faluz.app.dto.DeviceReleasedEventDTO;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class DevicesEventsProducer {
//    private static final String TOPIC_NAME = "devices.events";
//
//    @Autowired
//    private KafkaTemplate<String, DeviceReleasedEventDTO> kafkaTemplate;
//
//    public void sendDeviceReleasedEvent(DeviceReleasedEventDTO event) {
//        kafkaTemplate.send(TOPIC_NAME, event);
//    }
//}
//
//
