package net.mlorenzo.springbootkafkademo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StringMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringMessageService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;

    public StringMessageService(KafkaTemplate<String, String> kafkaTemplate,
                                @Value("${kafka.topic.name}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void sendMessage(String message) {
        LOGGER.info("Message send -> {}", message);
        kafkaTemplate.send(topicName, message);
    }

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        LOGGER.info("Message received -> {}", message);
    }
}
