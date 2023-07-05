package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.User;
import ar.edu.itba.bd2.redmond.model.enums.Bank;

import java.util.Optional;

public interface UserDao {
    User registerUser(String redmondId, String cbu, String cuil, String password, Bank bank);
    Optional<User> getUserByCbu(String cbu);
    Optional<User> getUserByRedmondId(String redmondId);
    Optional<User> getUserByCuil(String cuil);
}
