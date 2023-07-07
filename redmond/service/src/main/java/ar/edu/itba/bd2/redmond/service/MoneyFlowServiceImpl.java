package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.User;
import ar.edu.itba.bd2.redmond.persistence.MoneyFlowDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyFlowServiceImpl implements MoneyFlowService {

    private final MoneyFlowDao moneyFlowDao;

    @Autowired
    public MoneyFlowServiceImpl(MoneyFlowDao moneyFlowDao) {
        this.moneyFlowDao = moneyFlowDao;
    }

    @Override
    public void addTransactionToGraph(Transaction transaction) {
        moneyFlowDao.addTransactionToGraph(transaction);
    }

}
