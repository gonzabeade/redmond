package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.User;
import ar.edu.itba.bd2.redmond.persistence.LogDao;
import ar.edu.itba.bd2.redmond.persistence.UserDao;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    final private UserDao userDao;
    final private LogDao logDao;


    @Autowired
    public UserServiceImpl(UserDao userDao, LogDao logDao) {
        this.userDao = userDao;
        this.logDao = logDao;
    }

    @Override
    public User registerUser(String redmondId, String cbu, String cuil, String password) {
        //TODO: find bank and validate cbu/cuil
//        return userDao.registerUser(redmondId, cbu, cuil, password);
        throw new NotImplementedException();
    }

    @Override
//    @Cacheable("users::cbu")
    public Optional<User> getUserByCbu(String cbu) {
//        logDao.logTransactionInit(new Transaction("pepe", "mili", new BigDecimal(23)));
        return userDao.getUserByCbu(cbu);
    }

    @Override
    @Cacheable("users::cuil")
    public Optional<User> getUserByCuil(String cuil) {
        return userDao.getUserByCuil(cuil);
    }

    @Override
    @Cacheable("users::redmondId")
    public Optional<User> getUserByRedmondId(String redmondId) {
        return userDao.getUserByRedmondId(redmondId);
    }
}
