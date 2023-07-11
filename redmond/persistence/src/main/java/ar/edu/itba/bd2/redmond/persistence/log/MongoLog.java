package ar.edu.itba.bd2.redmond.persistence.log;

import ar.edu.itba.bd2.redmond.model.Transaction;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "logs")
public class MongoLog {

    public enum LogType {
        START_TX,
        DEBIT_TX,
        CREDIT_TX,
        COMMIT_TX,
        PANIC_TX,
        ROLLBACK_TX
    }

    private Instant timestamp;
    private String source;
    private String destination;
    private BigDecimal amount;
    private String transactionId;
    private LogType type;

    public static MongoLog fromTransaction(Transaction tx, LogType type) {
        MongoLog log = new MongoLog();
        log.source = tx.getSource();
        log.destination = tx.getDestination();
        log.amount = tx.getAmount();
        log.transactionId = String.valueOf(tx.getTransactionId());
        log.type = type;
        log.timestamp = Instant.now();
        return log;
    }

    private MongoLog() {
        //
    }

    public MongoLog(String source, String destination, BigDecimal amount, String transactionId, LogType type) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.transactionId = transactionId;
        this.type = type;
    }

    public Instant getTimestamp() {
        return timestamp;
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

    public LogType getType() {
        return type;
    }

    public void setType(LogType type) {
        this.type = type;
    }
}