package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.User;
import ar.edu.itba.bd2.redmond.persistence.SampleDao;
import ar.edu.itba.bd2.redmond.persistence.TransactionEventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    final private SampleDao sampleDao;
    final private TransactionEventDao transactionEventDao;
    @Autowired
    public UserServiceImpl(SampleDao sampleDao, TransactionEventDao transactionEventDao) {
        this.sampleDao = sampleDao;
        this.transactionEventDao = transactionEventDao;
    }

    @Override
    public User registerUser(String cbu, String cuil, String redmondId) {
        sampleDao.newSampleDao(new User(cbu, cuil, redmondId));
        transactionEventDao.initializeTransaction(new Transaction("x", "t", BigDecimal.ONE));
        return new User(cbu, cuil, redmondId);
    }

    @Override
    public Optional<User> getUserByCbu(String cbu) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByCuil(String cuil) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByRedmondId(String redmondId) {
        return Optional.empty();
    }
}
