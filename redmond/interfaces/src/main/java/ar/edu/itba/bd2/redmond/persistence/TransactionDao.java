package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.enums.Bank;
import ar.edu.itba.bd2.redmond.model.enums.TransactionStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransactionDao {
    Transaction create(
            String source,
            String destination,
            BigDecimal amount,
            String description
    );
    Transaction updateStatus(long transactionId, TransactionStatus status);
    Transaction updateDebitTransactionId(long transactionId, String debitTransactionId);
    Transaction updateCreditTransactionId(long transactionId, String creditTransactionId);
    Optional<Transaction> findById(long id);
    List<Transaction> getAllForUser(String redmondId);
}
