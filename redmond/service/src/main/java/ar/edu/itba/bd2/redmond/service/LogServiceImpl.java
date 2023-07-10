package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.events.*;
import ar.edu.itba.bd2.redmond.persistence.LogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);

    private final LogDao logDao;
    private final TransactionService transactionService;

    @Autowired
    public LogServiceImpl(LogDao logDao, TransactionService transactionService) {
        this.logDao = logDao;
        this.transactionService = transactionService;
    }

    @Override
    public void logInitTransactionEvent(InitTransactionEvent e) {
        try {
            Transaction t = transactionService.findById(e.getTransactionId()).orElseThrow();
            logDao.logInitTransaction(t);
        } catch (Exception e1) {
            LOGGER.warn("Error while logging init transaction event", e1);
        }
    }

    @Override
    public void logDebitTransactionEvent(DebitTransactionEvent e) {
        try {
            Transaction t = transactionService.findById(e.getTransactionId()).orElseThrow();
            logDao.logDebitTransaction(t);
        } catch (Exception e1) {
            LOGGER.warn("Error while logging debit transaction event", e1);
        }
    }

    @Override
    public void logCreditTransactionEvent(CreditTransactionEvent e) {
        try {
            Transaction t = transactionService.findById(e.getTransactionId()).orElseThrow();
            logDao.logCreditTransaction(t);
        } catch (Exception e1) {
            LOGGER.warn("Error while logging credit transaction event", e1);
        }
    }

    @Override
    public void logCommitTransactionEvent(CommitTransactionEvent e) {
        try {
            Transaction t = transactionService.findById(e.getTransactionId()).orElseThrow();
            logDao.logCommitTransaction(t);
        } catch (Exception e1) {
            LOGGER.warn("Error while logging commit transaction event", e1);
        }
    }

    @Override
    public void logPanicTransactionEvent(PanicTransactionEvent e) {
        try {
            Transaction t = transactionService.findById(e.getTransactionId()).orElseThrow();
            logDao.logPanicTransaction(t);
        } catch (Exception e1) {
            LOGGER.warn("Error while logging panic transaction event", e1);
        }
    }
}
