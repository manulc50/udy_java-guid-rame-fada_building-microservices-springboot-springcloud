package net.mlorenzo.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    private ErrorDetails handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
        return new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                // "false" para que no incluya infomación del cliente que realizó la petición http
                webRequest.getDescription(false),
                "EMPLOYEE_NOT_FOUND");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailAlreadyExistsException.class)
    private ErrorDetails handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, WebRequest webRequest) {
        return new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                // "false" para que no incluya infomación del cliente que realizó la petición http
                webRequest.getDescription(false),
                "EMPLOYEE_EMAIL_ALREADY_EXISTS");
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest webRequest) {
        ex.printStackTrace();
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                // "false" para que no incluya infomación del cliente que realizó la petición http
                webRequest.getDescription(false),
                "SERVER_INTERNAL_ERROR");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
