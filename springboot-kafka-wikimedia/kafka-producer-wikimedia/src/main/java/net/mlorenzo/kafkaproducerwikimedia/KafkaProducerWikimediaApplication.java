package net.mlorenzo.kafkaproducerwikimedia;

import lombok.AllArgsConstructor;
import net.mlorenzo.kafkaproducerwikimedia.service.WikimediaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@AllArgsConstructor
@SpringBootApplication
public class KafkaProducerWikimediaApplication {

    private final WikimediaService wikimediaService;

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerWikimediaApplication.class);
    }

    @Bean
    CommandLineRunner init() {
        return args -> wikimediaService.start(10);
    }
}