package ar.edu.itba.bd2.redmond.model.events;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.enums.TransactionEventType;

public class CreditTransactionEvent extends TransactionEvent {
    private String creditTransactionId;

    public CreditTransactionEvent() {
        super();
    }

    public CreditTransactionEvent(Transaction transaction) {
        super(transaction.getTransactionId());
        this.creditTransactionId = transaction.getCreditTransactionId();
    }

    public String getCreditTransactionId() {
        return creditTransactionId;
    }

    public void setCreditTransactionId(String creditTransactionId) {
        this.creditTransactionId = creditTransactionId;
    }

    @Override
    public String getEvent() {
        return TransactionEventType.CREDIT.toString();
    }
}
