package ar.edu.itba.bd2.redmond.model.events;

public abstract class TransactionEvent {
    private Long transactionId;

    TransactionEvent() {
        super();
    }

    public TransactionEvent(long transactionId) {
        this.transactionId = transactionId;
    }

    public abstract String getEvent();

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}
