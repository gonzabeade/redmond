package ar.edu.itba.bd2.redmond.controllers;

import ar.edu.itba.bd2.redmond.model.exceptions.TransactionNotFoundException;
import ar.edu.itba.bd2.redmond.model.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({
            UserNotFoundException.class,
            TransactionNotFoundException.class
    })
    public ResponseEntity<Map<String,String>> handle404Exception() {
        Map<String,String> body = new HashMap<>();
        body.put("error", "Not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
