package net.mlorenzo.departmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                         WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                // "false" para que no incluya infomación del cliente que realizó la petición http
                webRequest.getDescription(false),
                "DEPARTMENT_NOT_FOUND");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CodeAlreadyExistsException.class)
    private ResponseEntity<ErrorDetails> handleEmailAlreadyExistsException(CodeAlreadyExistsException ex,
                                                                           WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                // "false" para que no incluya infomación del cliente que realizó la petición http
                webRequest.getDescription(false),
                "DEPARTMENT_CODE_ALREADY_EXISTS");
        return ResponseEntity.badRequest().body(errorDetails);
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
