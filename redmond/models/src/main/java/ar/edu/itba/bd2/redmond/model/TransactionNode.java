package ar.edu.itba.bd2.redmond.model;

import org.springframework.data.neo4j.core.schema.*;

import java.util.HashSet;
import java.util.Set;

@Node
public class TransactionNode {

    @Id
    private String name;

    public TransactionNode() {
    }

    public TransactionNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Relationship(type = "TRANSACTION")
    private Set<TransactionRelationship> transactionTransactionRelationships = new HashSet<>();

    public void addTransactionRelationship(TransactionRelationship transactionRelationship) {
        transactionTransactionRelationships.add(transactionRelationship);
    }
}
