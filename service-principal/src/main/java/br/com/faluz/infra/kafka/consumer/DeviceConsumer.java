package br.com.faluz.infra.kafka.consumer;

import br.com.faluz.app.dto.DeviceEventDTO;
import br.com.faluz.app.service.DeviceEventService;
import br.com.faluz.app.service.ExternalApiService;
import br.com.faluz.infra.db.model.DeviceEvent;
import br.com.faluz.infra.kafka.producer.DeviceProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class DeviceConsumer implements AcknowledgingMessageListener<String, DeviceEvent> {

    private final DeviceEventService deviceEventService;
    private final ExternalApiService externalApiService;
    private final DeviceProducer deviceEventProducer;

    @Autowired
    public DeviceConsumer(DeviceEventService deviceEventService, ExternalApiService externalApiService, DeviceProducer deviceEventProducer) {
        this.deviceEventService = deviceEventService;
        this.externalApiService = externalApiService;
        this.deviceEventProducer = deviceEventProducer;
    }

    @Override
    @KafkaListener(topics = "contractions.events", containerFactory = "kafkaListenerContainerFactory")
    public void onMessage(ConsumerRecord<String, DeviceEvent> record, Acknowledgment acknowledgment) {
        DeviceEvent deviceEvent = record.value();
        try {
            // Step 2: Call API-REST
            externalApiService.callExternalApi(deviceEvent);

            // Step 3: Update Database
            deviceEventService.updateDevice(deviceEvent);

            // Step 5: Produce Kafka Event
            deviceEventProducer.sendEvent("devices.events",
                    new DeviceEvent(deviceEvent.getDevice()));

            acknowledgment.acknowledge();
        } catch (HttpClientErrorException e) {
            // Handle 4XX and 5XX errors and publish to retry or DLQ topics
            if (e.getStatusCode().is4xxClientError()) {
                // Publish to devices.events.retry
                deviceEventProducer.sendEvent("devices.events.retry", deviceEvent);
            } else if (e.getStatusCode().is5xxServerError()) {
                // Publish to devices.events.dlq
                deviceEventProducer.sendEvent("devices.events.dlq", deviceEvent);
            }
            acknowledgment.acknowledge();
        } catch (Exception e) {
            // Handle unexpected errors and stop the application
            throw new CustomException("Unexpected error occurred: " + e.getMessage());
        }
    }


}