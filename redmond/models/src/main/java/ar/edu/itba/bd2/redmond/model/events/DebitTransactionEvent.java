package ar.edu.itba.bd2.redmond.model.events;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.enums.TransactionEventType;

public class DebitTransactionEvent extends TransactionEvent {
    private String debitTransactionId;

    public DebitTransactionEvent() {
        super();
    }

    public DebitTransactionEvent(Transaction transaction) {
        super(transaction.getTransactionId());
        this.debitTransactionId = transaction.getDebitTransactionId();
    }

    @Override
    public String getEvent() {
        return TransactionEventType.DEBIT.toString();
    }

    public String getDebitTransactionId() {
        return debitTransactionId;
    }

    public void setDebitTransactionId(String debitTransactionId) {
        this.debitTransactionId = debitTransactionId;
    }
}
