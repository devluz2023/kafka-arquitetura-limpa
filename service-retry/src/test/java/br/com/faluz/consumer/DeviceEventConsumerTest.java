package br.com.faluz.consumer;

import br.com.faluz.app.dto.DeviceEventDTO;
import br.com.faluz.app.service.DeviceEventService;
import br.com.faluz.infra.kafka.consumer.DeviceConsumer;
import br.com.faluz.infra.kafka.producer.DeviceProducer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.support.KafkaHeaders;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;
import static org.springframework.kafka.support.KafkaHeaders.*;

@ExtendWith(MockitoExtension.class)
class DeviceEventConsumerTest {

}