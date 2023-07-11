package ar.edu.itba.bd2.redmond.model.events;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.enums.Bank;
import ar.edu.itba.bd2.redmond.model.enums.TransactionEventType;

import java.math.BigDecimal;

public class InitTransactionEvent extends TransactionEvent {
    private String source;
    private String destination;
    private String description;
    private BigDecimal amount;

    public InitTransactionEvent() {
    }

    public InitTransactionEvent(Transaction transaction) {
        super(transaction.getTransactionId());
        this.source = transaction.getSource();
        this.destination = transaction.getDestination();
        this.description = transaction.getDescription();
        this.amount = transaction.getAmount();
    }

    @Override
    public String getEvent() {
        return TransactionEventType.INIT.toString();
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
