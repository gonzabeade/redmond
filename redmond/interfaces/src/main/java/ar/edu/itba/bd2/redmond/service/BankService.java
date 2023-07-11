package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.BankAccount;
import ar.edu.itba.bd2.redmond.model.User;
import ar.edu.itba.bd2.redmond.model.enums.Bank;

import java.math.BigDecimal;
import java.util.Optional;

public interface BankService {
    String debit(User user, String description, BigDecimal amount);
    String credit(User user, String description, BigDecimal amount);
    Optional<BankAccount> findAccount(String cbu);
    void commitTransaction(Bank bank, String transactionId);
    void rollbackTransaction(Bank bank, String transactionId);
    default Optional<BankAccount> findAccount(User user) {
        return findAccount(user.getCbu());
    }
}
