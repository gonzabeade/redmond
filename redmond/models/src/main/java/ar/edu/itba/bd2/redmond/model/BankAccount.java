package ar.edu.itba.bd2.redmond.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BankAccount {
    private String cbu;
    private String cuil;

    protected BankAccount() {}

    public BankAccount(String cbu, String cuil) {
        this.cbu = cbu;
        this.cuil = cuil;
    }

    public String getCbu() {
        return cbu;
    }

    public String getCuil() {
        return cuil;
    }
}
