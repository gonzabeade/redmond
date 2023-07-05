package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.enums.Bank;
import ar.edu.itba.bd2.redmond.model.enums.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class TransactionDaoImpl implements TransactionDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private static final RowMapper<Transaction> TRANSACTION_ROW_MAPPER =
            (rs, rowNum) -> new Transaction(
                    rs.getLong("id"),
                    rs.getString("source"),
                    rs.getString("destination"),
                    rs.getBigDecimal("amount"),
                    rs.getString("description"),
                    rs.getString("debit_transaction_id"),
                    rs.getString("credit_transaction_id")
            );

    @Autowired
    public TransactionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("transactions")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Transaction create(String source, String destination, BigDecimal amount, String description) {
        Map<String,Object> params = new HashMap<>();
        params.put("source", source);
        params.put("destination", destination);
        params.put("amount", amount);
        params.put("description", description);
        long id = jdbcInsert.executeAndReturnKey(params).longValue();
        return new Transaction(id, source, destination, amount, description, null, null);
    }

    @Override
    public Transaction updateStatus(long transactionId, TransactionStatus status) {
        String sql = "UPDATE transactions SET status = ? WHERE id = ? RETURNING *";
        return jdbcTemplate.queryForObject(sql, TRANSACTION_ROW_MAPPER, status.name(), transactionId);
    }

    @Override
    public Transaction updateDebitTransactionId(long transactionId, String debitTransactionId) {
        String sql = "UPDATE transactions SET debit_transaction_id = ? WHERE id = ? RETURNING *";
        return jdbcTemplate.queryForObject(sql, TRANSACTION_ROW_MAPPER, debitTransactionId, transactionId);
    }

    @Override
    public Transaction updateCreditTransactionId(long transactionId, String creditTransactionId) {
        String sql = "UPDATE transactions SET credit_transaction_id = ? WHERE id = ? RETURNING *";
        return jdbcTemplate.queryForObject(sql, TRANSACTION_ROW_MAPPER, creditTransactionId, transactionId);
    }

    @Override
    public Optional<Transaction> findById(long id) {
        String sql = "SELECT * FROM transactions WHERE id = ?";
        Transaction t = jdbcTemplate.queryForObject(sql, TRANSACTION_ROW_MAPPER, id);
        return Optional.ofNullable(t);
    }
}
