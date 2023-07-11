package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.User;
import ar.edu.itba.bd2.redmond.persistence.MoneyFlowDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyFlowServiceImpl implements MoneyFlowService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoneyFlowServiceImpl.class);

    private final MoneyFlowDao moneyFlowDao;

    @Autowired
    public MoneyFlowServiceImpl(MoneyFlowDao moneyFlowDao) {
        this.moneyFlowDao = moneyFlowDao;
    }

    @Override
    public void addTransactionToGraph(Transaction transaction) {
        try {
            moneyFlowDao.addTransactionToGraph(transaction);
        } catch (Exception ex) {
            LOGGER.warn("Error commiting to graph", ex);
        }
    }
}
