package ar.edu.itba.bd2.redmond.model.exceptions;

public class CbuAlreadyExistsException extends RuntimeException {
    public CbuAlreadyExistsException() {
        super("CBU already exists");
    }
}
