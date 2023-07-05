package ar.edu.itba.bd2.redmond.dto;

import ar.edu.itba.bd2.redmond.model.Transaction;

import java.math.BigDecimal;

public class TransactionDto {
    private String id;
    private String source;
    private String destination;
    private BigDecimal amount;
    private String description;
    private String status;

    public TransactionDto(Transaction t) {
        this.id = String.valueOf(t.getTransactionId());
        this.source = t.getSource();
        this.destination = t.getDestination();
        this.amount = t.getAmount();
        this.description = t.getDescription();
        this.status = t.getStatus().toString().toLowerCase();
    }

    public String getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
