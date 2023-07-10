package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.events.*;

public interface LogService {
    void logInitTransactionEvent(InitTransactionEvent e);
    void logDebitTransactionEvent(DebitTransactionEvent e);
    void logCreditTransactionEvent(CreditTransactionEvent e);
    void logCommitTransactionEvent(CommitTransactionEvent e);
    void logPanicTransactionEvent(PanicTransactionEvent e);
}
