package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.Transaction;

public interface LogDao {

    void logTransactionInit(Transaction transaction);
}
