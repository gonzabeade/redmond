package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.TransactionNode;
import ar.edu.itba.bd2.redmond.model.Relationship;
import ar.edu.itba.bd2.redmond.model.Transaction;
import org.springframework.data.neo4j.core.Neo4jOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MoneyFlowDaoImpl implements MoneyFlowDao {

    // TODO - Integrate Neo4J
    private final Neo4jOperations neo4jOperations;

    public MoneyFlowDaoImpl(Neo4jOperations neo4jOperations) {
        this.neo4jOperations = neo4jOperations;
    }

    @Override
    @Transactional
    public void addTransactionToGraph(Transaction transaction) {
        // Create nodes for the sender and receiver
        TransactionNode sourceNode = new TransactionNode(transaction.getSource());
        TransactionNode destinationNode = new TransactionNode(transaction.getDestination());

        // Create a relationship representing the transaction
        Relationship transactionRelationship = new Relationship("TRANSACTION");
        transactionRelationship.setAmount(transaction.getAmount());

        // Set the relationship between the sender and receiver nodes
        transactionRelationship.setStartNode(sourceNode);
        transactionRelationship.setEndNode(destinationNode);

        // Save the sender, receiver, and the transaction relationship to the graph
        neo4jOperations.save(sourceNode);
        neo4jOperations.save(destinationNode);
        neo4jOperations.save(transactionRelationship);
    }
}
