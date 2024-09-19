package net.mlorenzo.orderservice.service;

import net.mlorenzo.basedomains.dto.Order;
import net.mlorenzo.basedomains.event.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final NewTopic topic;

    public OrderService(KafkaTemplate<String, OrderEvent> kafkaTemplate,
                        NewTopic topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void placeOrder(Order order) {
        order.setId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setMessage("Order status is in pending state");
        orderEvent.setStatus("PENDING");
        orderEvent.setOrder(order);
        sendMessage(orderEvent);
    }

    private void sendMessage(OrderEvent orderEvent) {
        LOG.info("Order event -> {}", orderEvent);
        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
