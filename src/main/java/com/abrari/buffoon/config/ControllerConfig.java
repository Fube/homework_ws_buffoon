package com.abrari.buffoon.config;

import com.abrari.buffoon.exceptions.SQLNotFoundException;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.TreeMap;

@ControllerAdvice
public class ControllerConfig {

    @ExceptionHandler(SQLNotFoundException.class)
    public ResponseEntity<Object> handleSQLException(
            SQLNotFoundException e
    ){

        val message = e.getMessage();

        return
                ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(
                                new TreeMap<String, Object>(){{
                                    put("timestamp", LocalDateTime.now().toString());
                                    put("message", message == null || message.length() == 0 ? "Entity not found" : message);
                                }}
                        );

    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(
            SQLIntegrityConstraintViolationException e
    ){
        return
                ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(
                                new TreeMap<String, Object>(){{
                                    put("timestamp", LocalDateTime.now().toString());
                                    put("message", e.getMessage());
                                }}
                        );
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(
            NullPointerException e
    ){
        return
                ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(
                                new TreeMap<String, Object>(){{
                                    put("timestamp", LocalDateTime.now().toString());
                                    put("message", e.getMessage());
                                }}
                        );
    }

}
