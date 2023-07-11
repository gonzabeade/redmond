package ar.edu.itba.bd2.redmond.model.enums;

public enum TransactionEventType {
    INIT,
    DEBIT,
    CREDIT,
    COMMIT,
    PANIC,
    ROLLBACK;

    @Override
    public String toString() {
        return name();
    }
}
