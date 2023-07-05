package ar.edu.itba.bd2.redmond.subscribers;

import ar.edu.itba.bd2.redmond.model.events.*;
import ar.edu.itba.bd2.redmond.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "transactions", groupId = "your-consumer-group")
public class TransactionEventSubscriber {
    private static final Logger logger = LoggerFactory.getLogger(YetAnotherSubscriber.class);

    private final TransactionService transactionService;

    @Autowired
    public TransactionEventSubscriber(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @KafkaHandler
    public void handleInitTransactionEvent(InitTransactionEvent e) {
        logger.debug("Init transaction event id: " + e.getTransactionId());
        transactionService.debitTransaction(e);
    }

    @KafkaHandler
    public void handleDebitTransactionEvent(DebitTransactionEvent e) {
        logger.debug("Debit transaction event id: " + e.getTransactionId());
        transactionService.creditTransaction(e);
    }

    @KafkaHandler
    public void handleCreditTransactionEvent(CreditTransactionEvent e) {
        logger.debug("Credit transaction event id: " + e.getTransactionId());
        transactionService.commitTransaction(e);
    }

    @KafkaHandler
    public void handleCommitTransactionEvent(CommitTransactionEvent e ){
        logger.debug("Commit transaction event id: " + e.getTransactionId());
    }

    @KafkaHandler
    public void handlePanicTransactionEvent(PanicTransactionEvent e) {
        logger.debug("Panic transaction event id: " + e.getTransactionId());
        transactionService.rollbackTransaction(e);
    }

    @KafkaHandler(isDefault = true)
    public void handleDefault(Object o) {
        logger.debug("Default event: " + o.toString());
    }
}
