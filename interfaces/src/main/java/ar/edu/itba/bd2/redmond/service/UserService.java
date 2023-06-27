package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.User;

import java.util.Optional;

public interface UserService {

    User registerUser(String cbu, String cuil, String redmondId);
    Optional<User> getUserByCbu(String cbu);
    Optional<User> getUserByCuil(String cuil);
    Optional<User> getUserByRedmondId(String redmondId);


}
