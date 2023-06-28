package ar.edu.itba.bd2.redmond;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class YetAnotherSubscriber {

    private static final Logger logger = LoggerFactory.getLogger(YetAnotherSubscriber.class);

    @KafkaListener(topics = "quickstart", groupId = "your-consumer-group")
    public void handleMessage(String message) {
        logger.info("Received message from Kafka: " + message);
    }
}
