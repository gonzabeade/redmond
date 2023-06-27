package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.service.TransactionEventDao;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionEventDaoImpl implements TransactionEventDao {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public TransactionEventDaoImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishEvent(String event) {
        kafkaTemplate.send("quickstart", event);
    }
}
