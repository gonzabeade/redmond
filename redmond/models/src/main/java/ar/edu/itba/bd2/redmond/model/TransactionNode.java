package ar.edu.itba.bd2.redmond.model;

import org.springframework.data.neo4j.core.schema.*;
@Node
public class TransactionNode {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public TransactionNode() {
    }

    public TransactionNode(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
