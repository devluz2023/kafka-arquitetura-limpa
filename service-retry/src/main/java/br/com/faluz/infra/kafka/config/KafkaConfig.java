package br.com.faluz.infra.kafka.config;

import br.com.faluz.app.dto.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Value("${kafka.bootstrapAddress:kafka:9092}")
    private String bootstrapAddress;

    private <T> ProducerFactory<String, T> createProducerFactory(Class<T> clazz) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    private <T> KafkaTemplate<String, T> createKafkaTemplate(Class<T> clazz) {
        return new KafkaTemplate<>(createProducerFactory(clazz));
    }

    @Bean
    public KafkaTemplate<String, DlqEventDTO> dlqEventDTOKafkaTemplate() {
        return createKafkaTemplate(DlqEventDTO.class);
    }



    @Bean
    public KafkaTemplate<String, DeviceReleasedEventDTO> deviceReleasedEventDTOKafkaTemplate() {
        return createKafkaTemplate(DeviceReleasedEventDTO.class);
    }

    @Bean
    public KafkaTemplate<String, RetryEventDTO> deviceReleasedEventRetryDTOKafkaTemplate() {
        return createKafkaTemplate(RetryEventDTO.class);
    }

    public ConsumerFactory<String, AccountCodeContractedEventDTO> accountCodeConsumerFactory() {
        JsonDeserializer<AccountCodeContractedEventDTO> deserializer = new JsonDeserializer<>(AccountCodeContractedEventDTO.class);
        deserializer.addTrustedPackages("*");

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AccountCodeContractedEventDTO> accountCodeKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AccountCodeContractedEventDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(accountCodeConsumerFactory());
        return factory;
    }

    public ConsumerFactory<String, DeviceContractedEventDTO> deviceConsumerFactory() {
        JsonDeserializer<DeviceContractedEventDTO> deserializer =
                new JsonDeserializer<>(DeviceContractedEventDTO.class);
        deserializer.addTrustedPackages("*");

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }


    public ConsumerFactory<String, RetryEventDTO> deviceEventRetryFactory() {
        JsonDeserializer<RetryEventDTO> deserializer =
                new JsonDeserializer<>(RetryEventDTO.class);
        deserializer.addTrustedPackages("*");

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DeviceContractedEventDTO> deviceKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DeviceContractedEventDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(deviceConsumerFactory());
        return factory;
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RetryEventDTO> deviceEventRetryKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, RetryEventDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(deviceEventRetryFactory());
        return factory;
    }
    // Add a common error handler configuration if needed
    @Bean
    public DefaultErrorHandler errorHandler() {
        return new DefaultErrorHandler();
    }
}
