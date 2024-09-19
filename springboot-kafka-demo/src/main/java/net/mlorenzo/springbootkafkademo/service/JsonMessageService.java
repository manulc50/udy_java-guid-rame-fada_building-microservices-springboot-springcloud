package net.mlorenzo.springbootkafkademo.service;

import net.mlorenzo.springbootkafkademo.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonMessageService.class);

    private final KafkaTemplate<String, User> kafkaTemplate;
    private final String topicName;

    public JsonMessageService(KafkaTemplate<String, User> kafkaTemplate,
                              @Value("${kafka.topic-json.name}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void sendMessage(User user) {
        LOGGER.info("Json Message send -> {}", user);
        Message<User> message = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
        kafkaTemplate.send(message);
    }

    @KafkaListener(topics = "${kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(User user) {
        LOGGER.info("Json Message received -> {}", user);
    }
}
