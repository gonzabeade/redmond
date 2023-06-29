package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.User;
import ar.edu.itba.bd2.redmond.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    final private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User registerUser(String redmondId, String cbu, String cuil, String password) {
        return userDao.registerUser(redmondId, cbu, cuil, password);
    }

    @Override
    @Cacheable("users::cbu")
    public Optional<User> getUserByCbu(String cbu) {
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
