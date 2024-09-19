package net.mlorenzo.springbootrestfulws.exception;

// Se comenta porque ahora usamos nuestro propio manejador de excepciones y no el manejador por defecto de
// Spring Boot
//@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
