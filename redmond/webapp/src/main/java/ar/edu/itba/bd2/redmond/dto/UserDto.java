package ar.edu.itba.bd2.redmond.dto;

import ar.edu.itba.bd2.redmond.model.User;

import java.math.BigDecimal;

public class UserDto {
    private final String redmondId;
    private final String cbu;
    private final String cuil;
    private final BigDecimal balance;

    public UserDto(User user) {
        this.redmondId = user.getRedmondId();
        this.cbu = user.getCbu();
        this.cuil = user.getCuil();
        this.balance = user.getBalance();
    }

    public String getRedmondId() {
        return redmondId;
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
}
