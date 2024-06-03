package br.com.faluz.infra.kafka.consumer;

import br.com.faluz.app.dto.*;
import br.com.faluz.app.service.EventService;
import br.com.faluz.app.service.ExternalApiService;
import br.com.faluz.domain.entity.Device;
import br.com.faluz.domain.usecase.impl.DeviceUseCase;
import br.com.faluz.infra.db.model.DeviceTable;
import br.com.faluz.infra.kafka.producer.DevicesEventsProducer;
import br.com.faluz.infra.kafka.producer.DlqEventsProducer;
import br.com.faluz.infra.kafka.producer.RetryEventsProducer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractionsEventsConsumer {
    private static final String TOPIC_NAME = "contractions.events";

    @Autowired
    private ExternalApiService apiService;

    @Autowired
    private DeviceUseCase deviceUseCase;

    @Autowired
    private DlqEventsProducer dlqEventsProducer;


    @Autowired
    private DevicesEventsProducer devicesEventsProducer;

    @Autowired
    private RetryEventsProducer retryEventsProducer;

    private final ObjectMapper objectMapper;

    private final EventService eventService;

    @Qualifier("deviceReleasedEventDTOKafkaTemplate")
    @Autowired
    private KafkaTemplate<String, DeviceReleasedEventDTO> deviceReleasedEventDTOKafkaTemplate;

    @Autowired
    public ContractionsEventsConsumer(EventService eventService) {
        this.eventService = eventService;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @KafkaListener(topics = TOPIC_NAME, groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void listenToEvents(ConsumerRecord<String, String> record, @Header("event_name") String eventName) {

        try {
            JsonNode rootNode = objectMapper.readTree(record.value());
            JsonNode dataNode = rootNode.path("data");

            if ("device.contracted".equals(eventName)) {
                DeviceContractedEventDTO deviceEvent = objectMapper.treeToValue(dataNode, DeviceContractedEventDTO.class);
                DeviceContractedEventDTO enrichedDeviceEvent = eventService.createDeviceContractedEvent(
                        deviceEvent.device(), deviceEvent.releaseDate(), deviceEvent.clientName());

                ResponseEntity<Device> response = apiService.callExternalApi(new Device(deviceEvent.device(), true));
                int statusCode = response.getStatusCodeValue();
                Device responseBody = response.getBody();

                if (statusCode == 201) {
                    DeviceTable deviceTable = new DeviceTable();
                    deviceTable.setReleaseDate(enrichedDeviceEvent.releaseDate());
                    deviceTable.setDevice(enrichedDeviceEvent.device());
                    deviceTable.setReleaseForUse(true);
                    deviceTable.setClientName(enrichedDeviceEvent.clientName());

                    this.deviceUseCase.updateDevice(deviceTable);
                    DeviceReleasedEventDTO deviceReleasedEvent = new DeviceReleasedEventDTO(
                            deviceTable.getDevice(),
                            deviceTable.getReleaseDate(),
                            deviceTable.getClientName()
                    );
                    this.devicesEventsProducer.sendDeviceReleasedEvent(deviceReleasedEvent);
                } else {
                    handleError(record, statusCode, "APIERROR");
                }

                log.info("Received device.contracted event: {}", enrichedDeviceEvent);
                log.info("API Response Status Code: {}", statusCode);
                log.info("API Response Body: {}", responseBody);
            } else if ("accountcode.contracted".equals(eventName)) {
                AccountCodeContractedEventDTO accountEvent = objectMapper.treeToValue(dataNode, AccountCodeContractedEventDTO.class);
                AccountCodeContractedEventDTO enrichedAccountEvent = eventService.createAccountCodeContractedEvent(
                        accountEvent.accountcode(), accountEvent.releaseDate(), accountEvent.clientName());
                log.info("Received accountcode.contracted event: {}", enrichedAccountEvent);
            }
        } catch (Exception e) {
            log.error("Error processing event", e);
            handleError(record, 500, "DATABASEERROR");
        }
    }

    private void handleError(ConsumerRecord<String, String> record, int statusCode, String errorEnumStr) {
        try {
            if (statusCode >= 400 && statusCode < 500) {
                DlqErrorEnum errorEnum = DlqErrorEnum.valueOf(errorEnumStr);
                DlqEventDTO dlqEvent = new DlqEventDTO(record.value(), errorEnum,
                        "Exception message");
                dlqEventsProducer.sendDlqEvent(dlqEvent);
            } else if (statusCode >= 500) {
                RetryErrorEnum errorEnum = RetryErrorEnum.valueOf(errorEnumStr);
                RetryEventDTO retryEvent = new RetryEventDTO(record.value(),
                        errorEnum, "Exception message");
                retryEventsProducer.sendRetryEvent(retryEvent);
            }
        } catch (IllegalArgumentException e) {
            log.error("Invalid errorEnum value: {}", errorEnumStr, e);

        }
    }
}
