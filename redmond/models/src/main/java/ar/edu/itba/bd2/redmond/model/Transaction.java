package ar.edu.itba.bd2.redmond.model;

import ar.edu.itba.bd2.redmond.model.enums.Bank;
import ar.edu.itba.bd2.redmond.model.enums.TransactionStatus;

import java.math.BigDecimal;

public class Transaction {
    private String source;
    private String destination;
    private BigDecimal amount;
    private long transactionId;
    private String description;
    private String debitTransactionId;
    private String creditTransactionId;
    private TransactionStatus status;

    public Transaction(
            long transactionId,
            String source,
            String destination,
            BigDecimal amount,
            String description,
            String debitTransactionId,
            String creditTransactionId,
            TransactionStatus status
    ) {
        this.transactionId = transactionId;
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.debitTransactionId = debitTransactionId;
        this.creditTransactionId = creditTransactionId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDebitTransactionId() {
        return debitTransactionId;
    }

    public void setDebitTransactionId(String debitTransactionId) {
        this.debitTransactionId = debitTransactionId;
    }

    public String getCreditTransactionId() {
        return creditTransactionId;
    }

    public void setCreditTransactionId(String creditTransactionId) {
        this.creditTransactionId = creditTransactionId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
