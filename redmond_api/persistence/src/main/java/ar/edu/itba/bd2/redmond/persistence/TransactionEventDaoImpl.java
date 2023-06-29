package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.persistence.event.InitTransactionEvent;
import ar.edu.itba.bd2.redmond.persistence.event.TransactionEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionEventDaoImpl implements TransactionEventDao {
    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    public TransactionEventDaoImpl(KafkaTemplate<String, TransactionEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void initializeTransaction(Transaction transaction) {
        kafkaTemplate.send("quickstart", InitTransactionEvent.fromTransaction(transaction));
    }
}
