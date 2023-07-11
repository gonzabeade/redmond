package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.enums.Bank;
import ar.edu.itba.bd2.redmond.model.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransactionDao extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.source = :redmondId OR t.destination = :redmondId ORDER BY timestamp desc")
    List<Transaction> findByUser(@Param("redmondId") String redmondId);
}
