package br.com.faluz;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = { "contractions.events" })
@DirtiesContext
@TestPropertySource(properties = {
        "spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "spring.kafka.consumer.bootstrap-servers=${spring.embedded.kafka.brokers}"
})
public class KafkaProducerTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final CountDownLatch latch = new CountDownLatch(1);
    private String receivedMessage = null;

    @KafkaListener(topics = "contractions.events", groupId = "testGroup")
    public void listen(ConsumerRecord<String, String> record) {
        receivedMessage = record.value();
        latch.countDown();
    }

    @Test
    public void testKafkaProducer() throws InterruptedException {
        // Create a sample message
        String key = "key1";
        String value = "{\"data\":{\"device\":\"457300-4862-d2535cb9-2cd5-4391-a527-db5c39544964\"," +
                "\"releaseDate\":\"2023-05-05\",\"clientName\":\"José da Silva Xavier\"}}";

        // Create a Kafka record
        ProducerRecord<String, String> record = new ProducerRecord<>("contractions.events", key, value);

        // Expect an exception when sending the message
        assertThrows(Exception.class, () -> {
            kafkaTemplate.send(record).get(); // Use .get() to wait for the message to be sent
        });

        // Optionally, verify that no message was received
        boolean messageConsumed = latch.await(10, TimeUnit.SECONDS);
        assertTrue(!messageConsumed, "Message was consumed when it should not have been");
    }
}
