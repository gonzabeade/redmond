package ar.edu.itba.bd2.redmond.model;

import ar.edu.itba.bd2.redmond.model.enums.Bank;

public class User {
    private String redmondId;
    private String password;
    private String cbu;
    private String cuil;
    private Bank bank;

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
}
