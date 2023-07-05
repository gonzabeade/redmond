package ar.edu.itba.bd2.redmond.model.events;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.enums.TransactionEventType;

public class PanicTransactionEvent extends TransactionEvent {
    public PanicTransactionEvent() {
        super();
    }

    public PanicTransactionEvent(Transaction transaction) {
        super(transaction.getTransactionId());
    }

    @Override
    public String getEvent() {
        return TransactionEventType.PANIC.toString();
    }
}
