package net.mlorenzo.employeeservice.exception;

// Se comenta porque ahora usamos nuestro propio manejador de excepciones y no el manejador por defecto de
// Spring Boot
//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
