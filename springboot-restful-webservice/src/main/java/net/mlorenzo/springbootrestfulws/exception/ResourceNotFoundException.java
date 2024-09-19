package net.mlorenzo.springbootrestfulws.exception;

// Se comenta porque ahora usamos nuestro propio manejador de excepciones y no el manejador por defecto de
// Spring Boot
//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s not found with %s : %d", resourceName, fieldName, fieldValue));
    }
}
