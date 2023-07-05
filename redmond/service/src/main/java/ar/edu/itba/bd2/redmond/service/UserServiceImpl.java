package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.BankAccount;
import ar.edu.itba.bd2.redmond.model.User;
import ar.edu.itba.bd2.redmond.model.exceptions.InvalidBankAccountException;
import ar.edu.itba.bd2.redmond.persistence.LogDao;
import ar.edu.itba.bd2.redmond.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    final private UserDao userDao;
    final private LogDao logDao;

    private final BankService bankService;

    @Autowired
    public UserServiceImpl(UserDao userDao, LogDao logDao, BankService bankService) {
        this.userDao = userDao;
        this.logDao = logDao;
        this.bankService = bankService;
    }

    @Override
    public User registerUser(String redmondId, String cbu, String cuil, String password) {
        BankAccount account = bankService.findAccount(cbu).orElseThrow(InvalidBankAccountException::new);
        if(!cuil.equals(account.getCuil())) throw new InvalidBankAccountException();

        return userDao.registerUser(redmondId, cbu, cuil, password, account.getBank());
    }

    @Override
    @Cacheable("users::cbu")
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
        //TODO: only return user data if the logged user is the same as the requested user

        return userDao.getUserByRedmondId(redmondId);
    }

    @Override
    public Optional<User> getUserByRedmondIdWithBalance(String redmondId) {
        //TODO: only return user data if the logged user is the same as the requested user

        Optional<User> maybeUser = getUserByRedmondId(redmondId);
        if(maybeUser.isEmpty()) return Optional.empty();
        User user = maybeUser.get();

        Optional<BankAccount> maybeAccount = bankService.findAccount(user.getCbu());
        if(maybeAccount.isEmpty()) return Optional.of(user);
        BankAccount account = maybeAccount.get();

        user.setBalance(account.getBalance());
        return Optional.of(user);
    }
}
