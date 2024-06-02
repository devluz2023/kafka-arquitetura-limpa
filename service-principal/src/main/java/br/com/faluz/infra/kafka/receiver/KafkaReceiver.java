package br.com.faluz.infra.kafka.receiver;

import br.com.faluz.app.dto.DeviceContractedEventDTO;
import br.com.faluz.app.dto.RetryEventDTO;
import br.com.faluz.app.dto.ErrorEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaReceiver {
    private final KafkaTemplate<String, RetryEventDTO> retryEventKafkaTemplate;
    private static final String CONTRACTIONS_EVENTS_TOPIC = "contractions.events";
    private static final String CONTRACTIONS_EVENTS_RETRY = "devices.events.retry";

    @KafkaListener(topics = CONTRACTIONS_EVENTS_TOPIC, groupId = "group_report", containerFactory = "deviceContractedKafkaListenerContainerFactory")
    public void listenContractionsEventsTopic(DeviceContractedEventDTO deviceContractedEventDTO) {
        try {
            log.info("Event received: {}.", deviceContractedEventDTO.clientName());
            if (deviceContractedEventDTO.device() == null) {
                log.error("Event without device.");
                throw new Exception("Device is null");
            }
            // Process event
        } catch (Exception e) {
            log.error("Application error", e);
            RetryEventDTO retryEvent = new RetryEventDTO(deviceContractedEventDTO.device(), ErrorEnum.APIERROR, e.getMessage());
            retryEventKafkaTemplate.send(CONTRACTIONS_EVENTS_RETRY, retryEvent);
        }
    }

    @KafkaListener(topics = CONTRACTIONS_EVENTS_RETRY, groupId = "group_report", containerFactory = "retryEventKafkaListenerContainerFactory")
    public void listenContractionsEventsRetryTopic(RetryEventDTO retryEventDTO) {
        log.info("Retrying event: {}.", retryEventDTO.device());
        // Retry logic
    }
}
