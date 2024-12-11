package com.example.epharmacy_backend.advice;

import com.example.epharmacy_backend.dto.response.ErrorDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorDTO> resourceNotFoundException(NoResourceFoundException exception) {
        var method = exception.getHttpMethod().toString();
        var url = exception.getResourcePath();

        var response = ErrorDTO.builder()
                .httpStatus(HttpStatus.NOT_FOUND.toString())
                .message("invalid request!")
                .error(method + " " + url + " does not exits!")
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorDTO> jwtException(JwtException exception) {
        String err;
        if (exception instanceof ExpiredJwtException) {
            err = "Jwt token is expired!";
        } else if (exception instanceof MalformedJwtException) {
            err = "Jwt token is malformed!";
        } else {
            err = "Jwt exception!";
        }

        var response = ErrorDTO.builder()
                .httpStatus(HttpStatus.UNAUTHORIZED.toString())
                .message("invalid bearer token")
                .error(err)
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> entityNotFoundException(EntityNotFoundException exception) {
        var response = ErrorDTO.builder()
                .httpStatus(HttpStatus.BAD_REQUEST.toString())
                .message("resource not found!")
                .error(exception.getLocalizedMessage())
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErrorDTO> entityExitsException(EntityExistsException exception) {
        var response = ErrorDTO.builder()
                .httpStatus(HttpStatus.BAD_REQUEST.toString())
                .message(exception.getMessage())
                .error("duplicate field exception!")
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> requestBodyValidationException(
            MethodArgumentNotValidException exception) {
        var errors = new HashMap<String, Object>();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> errors
                        .put(fieldError.getField(), fieldError.getDefaultMessage())
                );

        var response = ErrorDTO.builder()
                .httpStatus(HttpStatus.BAD_REQUEST.toString())
                .message("request body validation failed!")
                .error(errors)
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> unknownExceptionHandler(Exception exception) {
        var response = ErrorDTO.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .message("internal server error!")
                .error(null)
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
