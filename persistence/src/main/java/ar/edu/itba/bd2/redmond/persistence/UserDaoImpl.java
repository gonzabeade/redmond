package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<User> USER_ROW_MAPPER =
            (rs, rowNum) -> new User(
                    rs.getString("redmondId"),
                    rs.getString("cuil"),
                    rs.getString("cbu")
            );

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User registerUser(String redmondId, String cbu, String cuil, String password) {
        String sql = "INSERT INTO users (redmondId, cuil, cbu, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, redmondId, cuil, cbu, password);
        User user = new User(redmondId, cuil, cbu);
        return user;
    }

    @Override
    public Optional<User> getUserByCbu(String cbu) {
        String sql = "SELECT * FROM users WHERE cbu = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, USER_ROW_MAPPER, cbu);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUserByRedmondId(String redmondId) {
        String sql = "SELECT * FROM users WHERE redmondId = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, USER_ROW_MAPPER, redmondId);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUserByCuil(String cuil) {
        String sql = "SELECT * FROM users WHERE cuil = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, USER_ROW_MAPPER, cuil);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
