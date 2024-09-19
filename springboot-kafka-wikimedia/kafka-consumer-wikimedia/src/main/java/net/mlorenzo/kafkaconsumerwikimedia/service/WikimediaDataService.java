package net.mlorenzo.kafkaconsumerwikimedia.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mlorenzo.kafkaconsumerwikimedia.entity.WikimediaData;
import net.mlorenzo.kafkaconsumerwikimedia.repository.WikimediaDataRespository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class WikimediaDataService {

    private final WikimediaDataRespository respository;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        log.info("Message received -> {}", message);
        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(message);
        respository.save(wikimediaData);
    }
}
