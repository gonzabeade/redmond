package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class MoneyFlowDaoImpl implements MoneyFlowDao {

    // TODO - Integrate Neo4J

    @Override
    public void addTransactionToGraph(Transaction transaction) {

    }
}
