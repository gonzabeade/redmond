package ar.edu.itba.bd2.redmond.model.events;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.enums.TransactionEventType;

public class RollbackTransactionEvent extends TransactionEvent {
    public RollbackTransactionEvent() {
        super();
    }

    public RollbackTransactionEvent(Transaction transaction) {
        super(transaction.getTransactionId());
    }

    @Override
    public String getEvent() {
        return TransactionEventType.ROLLBACK.toString();
    }
}
