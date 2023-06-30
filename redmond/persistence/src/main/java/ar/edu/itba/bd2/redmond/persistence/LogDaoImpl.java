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
    public void logTransactionInit(Transaction transaction) {
        MongoLog mongoTransactionLog = MongoLog.fromTransaction(transaction, MongoLog.LogType.NEW_TX);
        mongoTemplate.save(mongoTransactionLog, "logs");
    }
}
