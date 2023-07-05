package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.events.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionEventDaoImpl implements TransactionEventDao {
    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    public TransactionEventDaoImpl(KafkaTemplate<String, TransactionEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        kafkaTemplate.setDefaultTopic("transactions");
    }

    @Override
    public void initializeTransactionEvent(Transaction transaction) {
        kafkaTemplate.sendDefault(new InitTransactionEvent(transaction));
    }

    @Override
    public void debitTransactionEvent(Transaction transaction) {
        kafkaTemplate.sendDefault(new DebitTransactionEvent(transaction));
    }

    @Override
    public void creditTransactionEvent(Transaction transaction) {
        kafkaTemplate.sendDefault(new CreditTransactionEvent(transaction));
    }

    @Override
    public void commitTransactionEvent(Transaction transaction) {
        kafkaTemplate.sendDefault(new CommitTransactionEvent(transaction));
    }

    @Override
    public void panicTransactionEvent(Transaction transaction) {
        kafkaTemplate.sendDefault(new PanicTransactionEvent(transaction));
    }

    @Override
    public void rollbackTransactionEvent(Transaction transaction) {
        kafkaTemplate.sendDefault(new RollbackTransactionEvent(transaction));
    }
}
