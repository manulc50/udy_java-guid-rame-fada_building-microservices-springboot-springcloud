package net.mlorenzo.employeeservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Esta anotación sólo es requerida cuando se quiere actualizar el valor de una propiedad que es inyectado desde
// un archivo de propiedades usando la anotación @Value
@RefreshScope
@RestController
public class MessageController {

    private final String message;
    private final Environment env;

    public MessageController(@Value("${spring.boot.message}") String message, Environment env) {
        this.message = message;
        this.env = env;
    }

    @GetMapping("message/value")
    public String getMessage() {
        return message;
    }

    @GetMapping("message/env")
    public String getMessageEnv() {
        return env.getProperty("spring.boot.message");
    }
}
