package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.Transaction;

public interface TransactionEventDao {
    void initializeTransactionEvent(Transaction transaction);
    void debitTransactionEvent(Transaction transaction);
    void creditTransactionEvent(Transaction transaction);
    void commitTransactionEvent(Transaction transaction);
    void panicTransactionEvent(Transaction transaction);
    void rollbackTransactionEvent(Transaction transaction);
}
