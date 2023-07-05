package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.User;

import java.util.Optional;

public interface UserService {

    User registerUser(String redmondId, String cbu, String cuil, String password);
    Optional<User> getUserByCbu(String cbu);
    Optional<User> getUserByRedmondId(String redmondId);
    Optional<User> getUserByRedmondIdWithBalance(String redmondId);
    Optional<User> getUserByCuil(String cuil);



}
