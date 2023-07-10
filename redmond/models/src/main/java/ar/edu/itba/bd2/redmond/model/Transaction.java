package ar.edu.itba.bd2.redmond.model;

import ar.edu.itba.bd2.redmond.model.enums.TransactionStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue
    private Long transactionId;

    @Column(nullable = false)
    private Instant timestamp;

    @Column(nullable = false, columnDefinition = "varchar(20)")
    private String source;

    @Column(nullable = false, columnDefinition = "varchar(20)")
    private String destination;

    @Column(nullable = false, columnDefinition = "numeric(18,2)")
    private BigDecimal amount;

    private String description;

    private String debitTransactionId;

    private String creditTransactionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(10)")
    private TransactionStatus status;

    public Transaction(
            String source,
            String destination,
            BigDecimal amount,
            String description
    ) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.description = description;
        this.status = TransactionStatus.PENDING;
        this.timestamp = Instant.now();
    }

    protected Transaction() {}

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
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

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
