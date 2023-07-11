package ar.edu.itba.bd2.redmond.subscribers;

import ar.edu.itba.bd2.redmond.model.events.*;
import ar.edu.itba.bd2.redmond.service.LogService;
import ar.edu.itba.bd2.redmond.service.MoneyFlowService;
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
    private static final Logger logger = LoggerFactory.getLogger(TransactionEventSubscriber.class);

    private final TransactionService transactionService;
    private final LogService logService;
    private final MoneyFlowService moneyFlowService;

    @Autowired
    public TransactionEventSubscriber(
            TransactionService transactionService,
            LogService logService,
            MoneyFlowService moneyFlowService
    ) {
        this.transactionService = transactionService;
        this.logService = logService;
        this.moneyFlowService = moneyFlowService;
    }

    @KafkaHandler
    public void handleInitTransactionEvent(InitTransactionEvent e) {
        logger.debug("Init transaction event id: " + e.getTransactionId());
        transactionService.debitTransaction(e);
        logService.logInitTransactionEvent(e);
    }

    @KafkaHandler
    public void handleDebitTransactionEvent(DebitTransactionEvent e) {
        logger.debug("Debit transaction event id: " + e.getTransactionId());
        transactionService.creditTransaction(e);
        logService.logDebitTransactionEvent(e);
    }

    @KafkaHandler
    public void handleCreditTransactionEvent(CreditTransactionEvent e) {
        logger.debug("Credit transaction event id: " + e.getTransactionId());
        transactionService.commitTransaction(e);
        logService.logCreditTransactionEvent(e);
    }

    @KafkaHandler
    public void handleCommitTransactionEvent(CommitTransactionEvent e ){
        logger.debug("Commit transaction event id: " + e.getTransactionId());
        logService.logCommitTransactionEvent(e);

        transactionService.findById(e.getTransactionId()).ifPresent(t -> {
            //noinspection Convert2MethodRef
            moneyFlowService.addTransactionToGraph(t);
        });
    }

    @KafkaHandler
    public void handlePanicTransactionEvent(PanicTransactionEvent e) {
        logger.debug("Panic transaction event id: " + e.getTransactionId());
        transactionService.rollbackTransaction(e);
        logService.logPanicTransactionEvent(e);
    }

    @KafkaHandler
    public void handleRollbackTransactionEvent(RollbackTransactionEvent e) {
        logger.info("Rollback transaction event id: " + e.getTransactionId());
        logService.logRollbackTransactionEvent(e);
    }

    @KafkaHandler(isDefault = true)
    public void handleDefault(Object o) {
        logger.debug("Default event: " + o.toString());
    }
}
