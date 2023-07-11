package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.BankAccount;
import ar.edu.itba.bd2.redmond.model.User;
import ar.edu.itba.bd2.redmond.model.enums.Bank;
import ar.edu.itba.bd2.redmond.persistence.BankApiDao;
import ar.edu.itba.bd2.redmond.persistence.BankApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {
    private final Map<Bank, BankApiDao> clients;

    @Autowired
    public BankServiceImpl(
            @Value("${banks.urls}") List<String> bankApiUrls
    ) {
        this.clients = new HashMap<>();

        for(int i = 0; i < bankApiUrls.size(); i++) {
            Bank bank = Bank.values()[i];
            String url = bankApiUrls.get(i);
            clients.put(bank, new BankApiClient(url));
        }
    }

    @Override
    public String debit(User user, String description, BigDecimal amount) {
        return getBankClient(user).debit(user.getCbu(), amount, description);
    }

    @Override
    public String credit(User user, String description, BigDecimal amount) {
        return getBankClient(user).credit(user.getCbu(), amount, description);
    }

    @Override
    public Optional<BankAccount> findAccount(String cbu) {
        for(Map.Entry<Bank,BankApiDao> entry : clients.entrySet()) {
            Bank bank = entry.getKey();
            BankApiDao client = entry.getValue();

            Optional<BankAccount> maybeAccount = client.findAccount(cbu);
            if(maybeAccount.isPresent()) {
                final BankAccount account = maybeAccount.get();
                return Optional.of(new BankAccount(
                        account.getCbu(),
                        account.getCuil(),
                        account.getBalance(),
                        bank
                ));
            }
        }
        return Optional.empty();
    }

    @Override
    public void commitTransaction(Bank bank, String transactionId) {
        getBankClient(bank).commitTransaction(transactionId);
    }

    @Override
    public void rollbackTransaction(Bank bank, String transactionId) {
        getBankClient(bank).rollbackTransaction(transactionId);
    }

    private BankApiDao getBankClient(Bank bank) {
        if(!clients.containsKey(bank)) throw new IllegalStateException();
        return clients.get(bank);
    }

    private BankApiDao getBankClient(User user) {
        return getBankClient(user.getBank());
    }
}
