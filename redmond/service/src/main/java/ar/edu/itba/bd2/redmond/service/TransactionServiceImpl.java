package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.User;
import ar.edu.itba.bd2.redmond.model.events.CreditTransactionEvent;
import ar.edu.itba.bd2.redmond.model.events.DebitTransactionEvent;
import ar.edu.itba.bd2.redmond.model.events.InitTransactionEvent;
import ar.edu.itba.bd2.redmond.model.events.PanicTransactionEvent;
import ar.edu.itba.bd2.redmond.model.exceptions.UserNotFoundException;
import ar.edu.itba.bd2.redmond.persistence.TransactionDao;
import ar.edu.itba.bd2.redmond.persistence.TransactionEventDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final BankService bankService;
    private final UserService userService;
    private final TransactionEventDao transactionEventDao;
    private final TransactionDao transactionDao;

    @Autowired
    public TransactionServiceImpl(
            BankService bankService,
            UserService userService,
            TransactionEventDao transactionEventDao,
            TransactionDao transactionDao
    ) {
        this.bankService = bankService;
        this.userService = userService;
        this.transactionEventDao = transactionEventDao;
        this.transactionDao = transactionDao;
    }

    @Override
    public Optional<Transaction> findById(long id) {
        return transactionDao.findById(id);
    }

    @Override
    public Transaction startTransaction(String source, String destination, BigDecimal amount, String description) {
        //Validate users exist
        userService.getUserByRedmondId(source).orElseThrow(UserNotFoundException::new);
        userService.getUserByRedmondId(destination).orElseThrow(UserNotFoundException::new);

        Transaction transaction = transactionDao.create(
                source,
                destination,
                amount,
                description
        );
        transactionEventDao.initializeTransactionEvent(transaction);

        return transaction;
    }

    @Override
    public void debitTransaction(InitTransactionEvent e) {
        Transaction t = findById(e.getTransactionId()).orElseThrow(IllegalStateException::new);
        User from = userService.getUserByRedmondId(t.getSource()).orElseThrow(IllegalStateException::new);

        try {
            String bankTransactionId = bankService.debit(from, t.getDescription(), t.getAmount());
            Transaction updated = transactionDao.updateDebitTransactionId(t.getTransactionId(), bankTransactionId);

            transactionEventDao.debitTransactionEvent(updated);
        } catch (Exception ex) {
            LOGGER.info("Error while debiting transaction", ex);
            transactionEventDao.panicTransactionEvent(t);
        }
    }

    @Override
    public void creditTransaction(DebitTransactionEvent e) {
        Transaction t = findById(e.getTransactionId()).orElseThrow(IllegalStateException::new);
        User to = userService.getUserByRedmondId(t.getDestination()).orElseThrow(IllegalStateException::new);

        try {
            String bankTransactionId = bankService.credit(to, t.getDescription(), t.getAmount());
            Transaction updated = transactionDao.updateCreditTransactionId(t.getTransactionId(), bankTransactionId);

            transactionEventDao.creditTransactionEvent(updated);
        } catch (Exception ex) {
            LOGGER.info("Error while crediting transaction", ex);
            transactionEventDao.panicTransactionEvent(t);
        }
    }

    @Override
    public void commitTransaction(CreditTransactionEvent e) {
        Transaction t = findById(e.getTransactionId()).orElseThrow(IllegalStateException::new);
        User from = userService.getUserByRedmondId(t.getSource()).orElseThrow(IllegalStateException::new);
        User to = userService.getUserByRedmondId(t.getDestination()).orElseThrow(IllegalStateException::new);

        try {
            bankService.commitTransaction(from.getBank(), t.getDebitTransactionId());
            bankService.commitTransaction(to.getBank(), t.getCreditTransactionId());

            transactionEventDao.commitTransactionEvent(t);
        } catch (Exception ex) {
            LOGGER.info("Error while committing transaction", ex);
            transactionEventDao.panicTransactionEvent(t);
        }
    }

    @Override
    public void rollbackTransaction(PanicTransactionEvent e) {
        Transaction t = findById(e.getTransactionId()).orElseThrow(IllegalStateException::new);
        User from = userService.getUserByRedmondId(t.getSource()).orElseThrow(IllegalStateException::new);
        User to = userService.getUserByRedmondId(t.getDestination()).orElseThrow(IllegalStateException::new);

        try {
            if(t.getCreditTransactionId() != null)
                bankService.rollbackTransaction(to.getBank(), t.getCreditTransactionId());

            if(t.getDebitTransactionId() != null)
                bankService.rollbackTransaction(from.getBank(), t.getDebitTransactionId());

            transactionEventDao.rollbackTransactionEvent(t);
        } catch (Exception ex) {
            LOGGER.warn("Error while rolling back transaction", ex);
            // volver a emitir evento de panic para reintentar???
            // (loop infinito, habría que hacer un sleep o algo similar)
            //
            // otra alternativa es que tras X tiempo los bancos cancelen la transacción automáticamente
        }
    }
}
