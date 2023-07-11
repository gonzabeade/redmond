package ar.edu.itba.bd2.redmond.model;

import ar.edu.itba.bd2.redmond.model.enums.Bank;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(columnDefinition = "varchar(20)")
    private String redmondId;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String password;

    @Column(nullable = false, columnDefinition = "char(22)")
    private String cbu;

    @Column(nullable = false, columnDefinition = "char(11)")
    private String cuil;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "char(6)")
    private Bank bank;

    @Transient
    private BigDecimal balance;

    public User() {
        // Just for caching
    }

    public User(String cbu, String cuil, String redmondId, String password, Bank bank) {
        this.cbu = cbu;
        this.cuil = cuil;
        this.redmondId = redmondId;
        this.password = password;
        this.bank = bank;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getRedmondId() {
        return redmondId;
    }

    public void setRedmondId(String redmondId) {
        this.redmondId = redmondId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
