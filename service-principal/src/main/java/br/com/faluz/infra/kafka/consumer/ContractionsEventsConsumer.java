package br.com.faluz.infra.kafka.consumer;

import br.com.faluz.app.dto.AccountCodeContractedEventDTO;
import br.com.faluz.app.dto.DeviceContractedEventDTO;
import br.com.faluz.app.service.EventService;
import br.com.faluz.app.service.ExternalApiService;
import br.com.faluz.domain.entity.Device;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractionsEventsConsumer {
    private static final String TOPIC_NAME = "contractions.events";

    @Autowired
    private ExternalApiService apiService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final EventService eventService;

    @KafkaListener(topics = TOPIC_NAME, groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenToEvents(ConsumerRecord<String, String> record,
                               @Header("event_name") String eventName){
        log.info("Received record: {}", record);
        log.info("Event name: {}", eventName);

        try {
            if ("device.contracted".equals(eventName)) {
                DeviceContractedEventDTO deviceEvent = objectMapper.readValue(record.value(), DeviceContractedEventDTO.class);
                DeviceContractedEventDTO enrichedDeviceEvent = eventService.createDeviceContractedEvent(
                        deviceEvent.device(),
                        deviceEvent.releaseDate(),
                        deviceEvent.clientName()
                );
                apiService.callExternalApi(new Device());
                log.info("Received device.contracted event: {}", enrichedDeviceEvent);
                // Add your logic to process device.contracted event
            } else if ("accountcode.contracted".equals(eventName)) {
                AccountCodeContractedEventDTO accountEvent = objectMapper.readValue(record.value(),
                        AccountCodeContractedEventDTO.class);
                AccountCodeContractedEventDTO enrichedAccountEvent = eventService.createAccountCodeContractedEvent(
                        accountEvent.accountcode(),
                        accountEvent.releaseDate(),
                        accountEvent.clientName()
                );
                log.info("Received accountcode.contracted event: {}", enrichedAccountEvent);
                // Add your logic to process accountcode.contracted event
            }
        } catch (Exception e) {
            log.error("Error processing event", e);
        }
    }
}
