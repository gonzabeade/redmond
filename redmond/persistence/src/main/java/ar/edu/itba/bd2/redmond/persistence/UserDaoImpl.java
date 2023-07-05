package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.User;
import ar.edu.itba.bd2.redmond.model.enums.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<User> USER_ROW_MAPPER =
            (rs, rowNum) -> new User(
                    rs.getString("cbu"),
                    rs.getString("cuil"),
                    rs.getString("redmondId"),
                    rs.getString("password"),
                    Bank.fromString(rs.getString("bank"))
            );

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User registerUser(String redmondId, String cbu, String cuil, String password, Bank bank) {
        String sql = "INSERT INTO users (redmondId, cuil, cbu, password, bank) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, redmondId, cuil, cbu, password, bank);
        return new User(redmondId, cuil, cbu, password, bank);
    }

    @Override
    public Optional<User> getUserByCbu(String cbu) {
        String sql = "SELECT * FROM users WHERE cbu = ?";
        User user = jdbcTemplate.queryForObject(sql, USER_ROW_MAPPER, cbu);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getUserByRedmondId(String redmondId) {
        String sql = "SELECT * FROM users WHERE redmondId = ?";
        User user = jdbcTemplate.queryForObject(sql, USER_ROW_MAPPER, redmondId);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getUserByCuil(String cuil) {
        String sql = "SELECT * FROM users WHERE cuil = ?";
        User user = jdbcTemplate.queryForObject(sql, USER_ROW_MAPPER, cuil);
        return Optional.ofNullable(user);
    }
}
