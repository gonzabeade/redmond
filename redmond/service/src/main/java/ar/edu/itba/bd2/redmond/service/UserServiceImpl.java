package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.BankAccount;
import ar.edu.itba.bd2.redmond.model.User;
import ar.edu.itba.bd2.redmond.model.exceptions.CbuAlreadyExistsException;
import ar.edu.itba.bd2.redmond.model.exceptions.InvalidBankAccountException;
import ar.edu.itba.bd2.redmond.model.exceptions.RedmondIdAlreadyExistsException;
import ar.edu.itba.bd2.redmond.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    final private UserDao userDao;

    private final BankService bankService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, BankService bankService, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.bankService = bankService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(String redmondId, String cbu, String cuil, String password) {
        BankAccount account = bankService.findAccount(cbu).orElseThrow(InvalidBankAccountException::new);
        if(!cuil.equals(account.getCuil())) throw new InvalidBankAccountException();

        if(getUserByCbu(cbu).isPresent())
            throw new CbuAlreadyExistsException();

        if(getUserByRedmondId(redmondId).isPresent())
            throw new RedmondIdAlreadyExistsException();

        return userDao.save(new User(cbu, cuil, redmondId, passwordEncoder.encode(password), account.getBank()));
    }

    @Override
    public boolean isLoginValid(String redmondId, String password) {
        Optional<User> maybeUser = getUserByRedmondId(redmondId);
        if(maybeUser.isEmpty()) return false;
        User user = maybeUser.get();

        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    @Cacheable(value = "users::cbu", unless = "#result == null")
    public Optional<User> getUserByCbu(String cbu) {
//        logDao.logTransactionInit(new Transaction("pepe", "mili", new BigDecimal(23)));
        return userDao.findByCbu(cbu);
    }

    @Override
    @Cacheable(value = "users::cuil", unless = "#result == null")
    public Optional<User> getUserByCuil(String cuil) {
        return userDao.findByCuil(cuil);
    }

    @Override
    @Cacheable(value = "users::redmondId", unless = "#result == null")
    public Optional<User> getUserByRedmondId(String redmondId) {
        return userDao.findByRedmondId(redmondId);
    }

    @Override
    public Optional<User> getUserByRedmondIdWithBalance(String redmondId) {
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
