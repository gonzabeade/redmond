package ar.edu.itba.bd2.redmond.persistence.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;

@Configuration
public class PersistenceConfiguration {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersistenceConfiguration(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            Resource resource = new ClassPathResource("schema.sql");
            ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), resource);
        };
    }

}