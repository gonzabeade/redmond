package ar.edu.itba.bd2.redmond.service;

public interface TransactionEventDao {

    void publishEvent(String event);
}
