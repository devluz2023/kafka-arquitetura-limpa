package br.com.faluz.infra.kafka.consumer;

import br.com.faluz.app.dto.DlqEventDTO;
import br.com.faluz.app.dto.DlqErrorEnum;
import br.com.faluz.app.dto.RetryErrorEnum;
import br.com.faluz.app.dto.RetryEventDTO;
import br.com.faluz.app.service.EventService;
import br.com.faluz.app.service.ExternalApiService;
import br.com.faluz.domain.entity.Device;
import br.com.faluz.domain.usecase.impl.DeviceUseCase;
import br.com.faluz.infra.db.model.DeviceTable;
import br.com.faluz.infra.kafka.producer.DeviceEventsDLQProducer;
import br.com.faluz.infra.kafka.producer.DevicesEventsRetryProducer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DevicesEventRetry {

    @Value("${application.retry.max-attempts}")
    private int maxAttempts;

    @Value("${application.retry.delay}")
    private long retryDelay;

    private static final String RETRY_TOPIC_NAME = "devices.events.retry";
    private static final String DLQ_TOPIC_NAME = "devices.events.dlq";

    @Autowired
    private ExternalApiService apiService;

    @Autowired
    private DeviceUseCase deviceUseCase;

    @Autowired
    private DeviceEventsDLQProducer dlqEventsProducer;

    @Autowired
    private DevicesEventsRetryProducer retryEventsProducer;

    private final ObjectMapper objectMapper;
    private final EventService eventService;

    @KafkaListener(topics = RETRY_TOPIC_NAME, groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void listenToEvents(ConsumerRecord<String, String> record, @Header("attempt-counter") String attemptCounterHeader) {
        int attemptCounter = Integer.parseInt(attemptCounterHeader) + 1;
        log.info("Processing event: {} with attempt: {}", record.value(), attemptCounter);

        try {

            processEvent(record.value());


            return;
        } catch (Exception e) {
            log.error("Error processing event: {}", record.value(), e);

            if (attemptCounter >= maxAttempts) {
                // Send to DLQ
                DlqEventDTO dlqEvent = new DlqEventDTO(record.value(), DlqErrorEnum.KAFKAPRODUCERERROR, e.getMessage());
                dlqEventsProducer.sendDlqEvent(dlqEvent);
            } else {

                RetryEventDTO retryEvent = new RetryEventDTO(record.value(), RetryErrorEnum.KAFKAPRODUCERERROR, e.getMessage());
                retryEventsProducer.sendRetryEvent(retryEvent);
            }
        }
    }

    private void processEvent(String eventValue) {

    }
}
