package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.TransactionNode;
import ar.edu.itba.bd2.redmond.model.TransactionRelationship;
import ar.edu.itba.bd2.redmond.model.Transaction;
import org.springframework.data.neo4j.core.Neo4jOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;

@Repository
public class MoneyFlowDaoImpl implements MoneyFlowDao {

    private final Neo4jOperations neo4jOperations;

    public MoneyFlowDaoImpl(Neo4jOperations neo4jOperations) {
        this.neo4jOperations = neo4jOperations;
    }

    @Override
    public void addTransactionToGraph(Transaction transaction) {
        String sourceName = transaction.getSource();
        String destinationName = transaction.getDestination();

        // Check if the nodes already exist in the graph
        String cypherQuery = "MATCH (n:Person {name: $name}) RETURN n";
        Map<String, Object> p1 = Collections.singletonMap("name", transaction.getSource());
        Map<String, Object> p2 = Collections.singletonMap("name", transaction.getDestination());

        TransactionNode sourceNode = neo4jOperations
                .findOne(cypherQuery, p1, TransactionNode.class)
                .orElseGet(() -> new TransactionNode(sourceName));

        TransactionNode destinationNode = neo4jOperations
                .findOne(cypherQuery, p2, TransactionNode.class)
                .orElseGet(() -> new TransactionNode(destinationName));

        // Create a relationship representing the transaction
        TransactionRelationship transactionRelationship = new TransactionRelationship("TRANSACTION");
        transactionRelationship.setAmount(transaction.getAmount());

        // Set the relationship between the sender and receiver nodes
        transactionRelationship.setStartNode(sourceNode);
        transactionRelationship.setEndNode(destinationNode);

        // Establish the relationship to nodes
        sourceNode.addTransactionRelationship(transactionRelationship);
        destinationNode.addTransactionRelationship(transactionRelationship);

        // Save the sender, receiver, and the transaction relationship to the graph
        neo4jOperations.save(sourceNode);
        neo4jOperations.save(destinationNode);
//        neo4jOperations.save(transactionRelationship);
    }

}
