package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.Transaction;

public interface LogDao {
    void logInitTransaction(Transaction transaction);
    void logDebitTransaction(Transaction transaction);
    void logCreditTransaction(Transaction transaction);
    void logCommitTransaction(Transaction transaction);
    void logPanicTransaction(Transaction transaction);
}
