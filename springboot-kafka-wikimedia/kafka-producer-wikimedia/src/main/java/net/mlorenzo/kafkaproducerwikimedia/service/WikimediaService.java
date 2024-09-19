package net.mlorenzo.kafkaproducerwikimedia.service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;
    private final String wikimediaUri;

    public WikimediaService(KafkaTemplate<String, String> kafkaTemplate,
                            @Value("${kafka.topic.name}") String topicName,
                            @Value("${wikimedia.uri}") String wikimediaUri) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
        this.wikimediaUri = wikimediaUri;
    }

    public void start(int minutes) throws InterruptedException {
        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topicName);
        EventSource.Builder eventSourceBuilder = new EventSource.Builder(eventHandler, URI.create(wikimediaUri));
        EventSource eventSource = eventSourceBuilder.build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(minutes);
    }
}
