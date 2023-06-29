package ar.edu.itba.bd2.redmond.persistence.event;

import ar.edu.itba.bd2.redmond.model.Transaction;

import java.math.BigDecimal;

public class InitTransactionEvent extends TransactionEvent {

    private String source;
    private String destination;
    private BigDecimal amount;

    public InitTransactionEvent(String source, String destination, BigDecimal amount) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
    }

    public static InitTransactionEvent fromTransaction(Transaction t) {
        return new InitTransactionEvent(t.getSource(), t.getDestination(), t.getAmount());
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
