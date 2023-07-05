package ar.edu.itba.bd2.redmond.model;

import ar.edu.itba.bd2.redmond.model.enums.Bank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BankAccount {
    private String cbu;
    private String cuil;
    private BigDecimal balance;
    private Bank bank;

    protected BankAccount() {}

    public BankAccount(String cbu, String cuil, BigDecimal balance, Bank bank) {
        this.cbu = cbu;
        this.cuil = cuil;
        this.bank = bank;
        this.balance = balance;
    }

    public String getCbu() {
        return cbu;
    }

    public String getCuil() {
        return cuil;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Bank getBank() {
        return bank;
    }
}
