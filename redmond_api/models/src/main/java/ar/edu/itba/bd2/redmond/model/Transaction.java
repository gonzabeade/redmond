package ar.edu.itba.bd2.redmond.model;

import java.math.BigDecimal;

public class Transaction {

    private String source;
    private String destination;
    private BigDecimal amount;
    private String transactionId;


    public Transaction(String source, String destination, BigDecimal amount) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
