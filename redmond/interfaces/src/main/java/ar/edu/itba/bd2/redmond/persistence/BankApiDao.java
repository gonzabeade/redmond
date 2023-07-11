package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.BankAccount;

import java.math.BigDecimal;
import java.util.Optional;

public interface BankApiDao {
    String transfer(String fromAccount, String toAccount, BigDecimal amount, String description);
    String debit(String account, BigDecimal amount, String description);
    String credit(String account, BigDecimal amount, String description);
    void rollbackTransaction(String transactionId);
    void commitTransaction(String transactionId);

    Optional<BankAccount> findAccount(String cbu);
}
