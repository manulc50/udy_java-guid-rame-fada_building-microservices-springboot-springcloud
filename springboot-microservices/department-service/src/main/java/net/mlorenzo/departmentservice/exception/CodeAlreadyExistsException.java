package net.mlorenzo.departmentservice.exception;

// Se comenta porque ahora usamos nuestro propio manejador de excepciones y no el manejador por defecto de
// Spring Boot
//@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CodeAlreadyExistsException extends RuntimeException {

    public CodeAlreadyExistsException(String message) {
        super(message);
    }
}
