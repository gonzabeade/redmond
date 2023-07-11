package ar.edu.itba.bd2.redmond.model.exceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("Insufficient funds");
    }
}
