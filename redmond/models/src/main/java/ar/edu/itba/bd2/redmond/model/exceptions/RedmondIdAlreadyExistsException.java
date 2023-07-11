package ar.edu.itba.bd2.redmond.model.exceptions;

public class RedmondIdAlreadyExistsException extends RuntimeException {
    public RedmondIdAlreadyExistsException() {
        super("Redmond ID already exists");
    }
}
