package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.MyEvent;
import ar.edu.itba.bd2.redmond.model.SampleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SampleDaoImpl implements SampleDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SampleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SampleModel newSampleDao(String name) {

        // Example database operation using JDBC template
        String sql = "INSERT INTO sample (column1, column2) VALUES (?, ?)";
        jdbcTemplate.update(sql, "value1", "value2");

        return new SampleModel(name);
    }
}
