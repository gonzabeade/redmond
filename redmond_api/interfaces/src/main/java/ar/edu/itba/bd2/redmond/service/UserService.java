package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.User;

import java.util.Optional;

public interface UserService {

    User registerUser(String cbu, String cuil, String redmondId);
    User getUserByCbu(String cbu);
    User getUserByCuil(String cuil);
    User getUserByRedmondId(String redmondId);


}
