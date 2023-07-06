package ar.edu.itba.bd2.redmond.model.enums;

public enum TransactionStatus {
    PENDING,
    APPROVED,
    REJECTED;

    public static TransactionStatus fromString(String status) {
        for(TransactionStatus transactionStatus : TransactionStatus.values()) {
            if(transactionStatus.name().equalsIgnoreCase(status)) {
                return transactionStatus;
            }
        }

        throw new IllegalArgumentException("Invalid transaction status: " + status);
    }
}
