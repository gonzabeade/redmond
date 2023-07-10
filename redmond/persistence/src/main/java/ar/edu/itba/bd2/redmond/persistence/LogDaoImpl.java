package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.persistence.log.MongoLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogDaoImpl implements LogDao {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public LogDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void logInitTransaction(Transaction transaction) {
        logTransactionEvent(transaction, MongoLog.LogType.START_TX);
    }

    @Override
    public void logDebitTransaction(Transaction transaction) {
        logTransactionEvent(transaction, MongoLog.LogType.DEBIT_TX);
    }

    @Override
    public void logCreditTransaction(Transaction transaction) {
        logTransactionEvent(transaction, MongoLog.LogType.CREDIT_TX);
    }

    @Override
    public void logCommitTransaction(Transaction transaction) {
        logTransactionEvent(transaction, MongoLog.LogType.COMMIT_TX);
    }

    @Override
    public void logPanicTransaction(Transaction transaction) {
        logTransactionEvent(transaction, MongoLog.LogType.PANIC_TX);
    }

    private void logTransactionEvent(Transaction transaction, MongoLog.LogType logType) {
        MongoLog mongoTransactionLog = MongoLog.fromTransaction(transaction, logType);
        mongoTemplate.save(mongoTransactionLog, "logs");
    }
}
