package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.User;
import ar.edu.itba.bd2.redmond.model.enums.Bank;
import ar.edu.itba.bd2.redmond.model.exceptions.CbuAlreadyExistsException;
import ar.edu.itba.bd2.redmond.model.exceptions.RedmondIdAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private static final RowMapper<User> USER_ROW_MAPPER =
            (rs, rowNum) -> new User(
                    rs.getString("cbu"),
                    rs.getString("cuil"),
                    rs.getString("redmondId").trim(),
                    rs.getString("password"),
                    Bank.fromString(rs.getString("bank"))
            );

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users");
    }

    @Override
    public User registerUser(String redmondId, String cbu, String cuil, String password, Bank bank) {
        if(getUserByCbu(cbu).isPresent())
            throw new CbuAlreadyExistsException();

        if(getUserByRedmondId(redmondId).isPresent())
            throw new RedmondIdAlreadyExistsException();

        Map<String,Object> params = new HashMap<>();
        params.put("redmondId", redmondId);
        params.put("cuil", cuil);
        params.put("cbu", cbu);
        params.put("password", password);
        params.put("bank", bank.toString());
        jdbcInsert.execute(params);

        return new User(redmondId, cuil, cbu, password, bank);
    }

    @Override
    public Optional<User> getUserByCbu(String cbu) {
        String sql = "SELECT * FROM users WHERE cbu = ?";
        return jdbcTemplate.query(sql, USER_ROW_MAPPER, cbu).stream().findFirst();
    }

    @Override
    public Optional<User> getUserByRedmondId(String redmondId) {
        String sql = "SELECT * FROM users WHERE redmondId = ?";
        return jdbcTemplate.query(sql, USER_ROW_MAPPER, redmondId).stream().findFirst();
    }

    @Override
    public Optional<User> getUserByCuil(String cuil) {
        String sql = "SELECT * FROM users WHERE cuil = ?";
        return jdbcTemplate.query(sql, USER_ROW_MAPPER, cuil).stream().findFirst();
    }
}
