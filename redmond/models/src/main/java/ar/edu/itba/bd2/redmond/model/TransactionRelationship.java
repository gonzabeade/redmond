package ar.edu.itba.bd2.redmond.model;

import org.springframework.data.neo4j.core.schema.*;

import java.math.BigDecimal;

@RelationshipProperties
public class TransactionRelationship {
    @Id
    @GeneratedValue
    private Long id;

    private TransactionNode startNode;

    @TargetNode
    private TransactionNode endNode;
    private String type;

    // Add additional properties related to the relationship
    private BigDecimal amount;

    public TransactionRelationship() {
    }

    public TransactionRelationship(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionNode getStartNode() {
        return startNode;
    }

    public void setStartNode(TransactionNode startNode) {
        this.startNode = startNode;
    }

    public TransactionNode getEndNode() {
        return endNode;
    }

    public void setEndNode(TransactionNode endNode) {
        this.endNode = endNode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
