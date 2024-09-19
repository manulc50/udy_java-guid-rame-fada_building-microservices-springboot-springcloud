package net.mlorenzo.kafkaproducerwikimedia.service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
@Slf4j
public class WikimediaChangesHandler implements EventHandler {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    // Sólo implementamos este método porque es el único que vamos a usar y se ejecutará automáticamente cada vez
    // que se publique un nuevo mensaje en Wikimedia
    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        final String message = messageEvent.getData();
        log.info("Event data -> {}", message);
        kafkaTemplate.send(topicName, message);
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
