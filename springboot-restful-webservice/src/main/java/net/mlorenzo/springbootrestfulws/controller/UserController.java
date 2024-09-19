package net.mlorenzo.springbootrestfulws.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.mlorenzo.springbootrestfulws.dto.UserDto;
import net.mlorenzo.springbootrestfulws.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.created(null).body(userService.createUser(userDto));
    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable("id") Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("{userId}")
    public UserDto updateUser(@PathVariable Long userId, @Valid @RequestBody UserDto userDto) {
        return userService.updateUser(userId, userDto);
    }

    @PatchMapping("{userId}")
    public UserDto updateUserByFields(@PathVariable Long userId, @RequestBody Map<String, Object> fields) {
        return userService.updateUserByFields(userId, fields);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Se comentan los 2 siguientes método porque ahora usamos un manejador de excepciones centralizado para todos
    // los controladores
    // Manejo de las excepciones de tipo "ResourceNotFoundException" para este controlador
    /*@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    private ErrorDetails handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
        return new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                // "false" para que no incluya infomación del cliente que realizó la petición http
                webRequest.getDescription(false),
                "USER_NOT_FOUND");
    }*/

    // Manejo de las excepciones de tipo "EmailAlreadyExistsException" para este controlador
    /*@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailAlreadyExistsException.class)
    private ErrorDetails handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, WebRequest webRequest) {
        return new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                // "false" para que no incluya infomación del cliente que realizó la petición http
                webRequest.getDescription(false),
                "USER_EMAIL_ALREADY_EXISTS");
    }*/
}
