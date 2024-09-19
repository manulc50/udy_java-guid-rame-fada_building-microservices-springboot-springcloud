package net.mlorenzo.emailservice.service;

import net.mlorenzo.basedomains.event.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    private void consume(OrderEvent orderEvent) {
        LOG.info("Order event received -> {}", orderEvent);
    }
}
