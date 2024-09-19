package net.mlorenzo.springbootrestfulws.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    private ErrorDetails handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
        return new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                // "false" para que no incluya infomación del cliente que realizó la petición http
                webRequest.getDescription(false),
                "USER_NOT_FOUND");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailAlreadyExistsException.class)
    private ErrorDetails handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, WebRequest webRequest) {
        return new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                // "false" para que no incluya infomación del cliente que realizó la petición http
                webRequest.getDescription(false),
                "USER_EMAIL_ALREADY_EXISTS");
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                // "false" para que no incluya infomación del cliente que realizó la petición http
                webRequest.getDescription(false),
                "SERVER_INTERNAL_ERROR");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = ex.getFieldErrors().stream()
                // Versión simplificada de la expresión "(error -> error.getField(), error -> error.getDefaultMessage())"
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
