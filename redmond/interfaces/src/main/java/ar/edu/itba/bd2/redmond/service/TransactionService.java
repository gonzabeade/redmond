package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.events.*;

import java.math.BigDecimal;
import java.util.Optional;

public interface TransactionService {
    Optional<Transaction> findById(long id);
    Transaction startTransaction(String source, String destination, BigDecimal amount, String description);
    void debitTransaction(InitTransactionEvent event);
    void creditTransaction(DebitTransactionEvent event);
    void commitTransaction(CreditTransactionEvent event);
    void rollbackTransaction(PanicTransactionEvent event);
}
