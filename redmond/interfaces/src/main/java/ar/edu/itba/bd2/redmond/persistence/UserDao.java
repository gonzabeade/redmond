package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByCbu(String cbu);
    Optional<User> findByRedmondId(String redmondId);
    Optional<User> findByCuil(String cuil);
}
