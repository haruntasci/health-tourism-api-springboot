package com.allianz.healthtourism.util.advisor;

import com.allianz.healthtourism.exception.CityIsNotSuitableException;
import com.allianz.healthtourism.exception.TimeIsNotSuitableException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<Object> handlePSQLException(PSQLException exception, WebRequest request) {
        Map<String, Object> exceptionBody = new HashMap<>();
        exceptionBody.put("timestamp", LocalDateTime.now());
        exceptionBody.put("message", exception.getMessage());
        return new ResponseEntity<>(exceptionBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TimeIsNotSuitableException.class)
    public ResponseEntity<Object> handleTimeIsNotSuitableException(TimeIsNotSuitableException exception, WebRequest request) {
        Map<String, Object> exceptionBody = new HashMap<>();
        exceptionBody.put("timestamp", LocalDateTime.now());
        exceptionBody.put("message", exception.getMessage());
        return new ResponseEntity<>(exceptionBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CityIsNotSuitableException.class)
    public ResponseEntity<Object> handleTimeIsNotSuitableException(CityIsNotSuitableException exception, WebRequest request) {
        Map<String, Object> exceptionBody = new HashMap<>();
        exceptionBody.put("timestamp", LocalDateTime.now());
        exceptionBody.put("message", exception.getMessage());
        return new ResponseEntity<>(exceptionBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
