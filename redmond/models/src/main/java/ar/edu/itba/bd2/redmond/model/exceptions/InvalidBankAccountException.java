package ar.edu.itba.bd2.redmond.model.exceptions;

public class InvalidBankAccountException extends RuntimeException {
    public InvalidBankAccountException() {
        super("Bank account does not exist");
    }
}
