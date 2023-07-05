package ar.edu.itba.bd2.redmond.model.events;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.enums.TransactionEventType;

public class CommitTransactionEvent extends TransactionEvent {
    public CommitTransactionEvent() {
        super();
    }

    public CommitTransactionEvent(Transaction transaction) {
        super(transaction.getTransactionId());
    }

    @Override
    public String getEvent() {
        return TransactionEventType.CREDIT.toString();
    }
}
