package br.com.faluz.infra.kafka.consumer;

import br.com.faluz.app.dto.AccountCodeContractedEventDTO;
import br.com.faluz.app.dto.DeviceContractedEventDTO;
import br.com.faluz.app.service.ExternalApiService;
import br.com.faluz.domain.entity.Device;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractionsEventsConsumer {
    private static final String TOPIC_NAME = "contractions.events";
    @Autowired
    private ExternalApiService apiService;

    @KafkaListener(topics = TOPIC_NAME, groupId = "${spring.kafka.consumer.group-id}")
    public void listenToAccountCodeContractedEvent(String message) {
        apiService.callExternalApi(new Device());
        System.out.println("Received accountcode.contracted event: " + message);
        // Add your logic to process accountcode.contracted event
    }
}
