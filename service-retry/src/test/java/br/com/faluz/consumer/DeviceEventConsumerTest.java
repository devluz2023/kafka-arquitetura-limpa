//package br.com.faluz.consumer;
//
//import br.com.faluz.app.dto.DeviceEventDTO;
//import br.com.faluz.app.service.DeviceEventService;
//import br.com.faluz.infra.kafka.consumer.DeviceEventConsumer;
//import br.com.faluz.infra.kafka.producer.DeviceEventProducer;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.kafka.support.KafkaHeaders;
//
//import java.nio.charset.StandardCharsets;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class DeviceEventConsumerTest {
//
//    @Mock
//    private DeviceEventService deviceEventService;
//
//    @Mock
//    private DeviceEventProducer deviceEventProducer;
//
//    @InjectMocks
//    private DeviceEventConsumer deviceEventConsumer;
//
//    @Test
//    void listenDeviceEvents_success() {
//        String message = "{\"device\":\"device123\",\"released\":true}";
//        ConsumerRecord<String, String> record = new ConsumerRecord<>("devices.events.retry", 0, 0L, null, message);
//        record.headers().add(KafkaHeaders.RECEIVED_MESSAGE_KEY, message.getBytes(StandardCharsets.UTF_8));
//        record.headers().add("attempt-counter", "1".getBytes(StandardCharsets.UTF_8));
//
//        deviceEventConsumer.listenDeviceEvents(record);
//
//        verify(deviceEventService, times(1)).processDeviceEvent(any(DeviceEventDTO.class));
//    }
//
//    @Test
//    void listenDeviceEvents_failure_retry() {
//        String message = "{\"device\":\"device123\",\"released\":true}";
//        ConsumerRecord<String, String> record = new ConsumerRecord<>("devices.events.retry", 0, 0L, null, message);
//        record.headers().add(KafkaHeaders.RECEIVED_MESSAGE_KEY, message.getBytes(StandardCharsets.UTF_8));
//        record.headers().add("attempt-counter", "1".getBytes(StandardCharsets.UTF_8));
//
//        doThrow(new RuntimeException("Error")).when(deviceEventService).processDeviceEvent(any(DeviceEventDTO.class));
//
//        deviceEventConsumer.listenDeviceEvents(record);
//
//        verify(deviceEventService, times(1)).processDeviceEvent(any(DeviceEventDTO.class));
//        verify(deviceEventProducer, times(1)).sendRetryEvent(message, 1);
//    }
//
//    @Test
//    void listenDeviceEvents_failure_dlq() {
//        String message = "{\"device\":\"device123\",\"released\":true}";
//        ConsumerRecord<String, String> record = new ConsumerRecord<>("devices.events.retry", 0, 0L, null, message);
//        record.headers().add(KafkaHeaders.RECEIVED_MESSAGE_KEY, message.getBytes(StandardCharsets.UTF_8));
//        record.headers().add("attempt-counter", "5".getBytes(StandardCharsets.UTF_8));
//
//        doThrow(new RuntimeException("Error")).when(deviceEventService).processDeviceEvent(any(DeviceEventDTO.class));
//
//        deviceEventConsumer.listenDeviceEvents(record);
//
//        verify(deviceEventService, times(1)).processDeviceEvent(any(DeviceEventDTO.class));
//        verify(deviceEventProducer, times(1)).sendDlqEvent(message, any(RuntimeException.class));
//    }
//}