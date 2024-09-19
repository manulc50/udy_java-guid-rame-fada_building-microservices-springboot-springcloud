package net.mlorenzo.springbootkafkademo.controller;

import net.mlorenzo.springbootkafkademo.payload.User;
import net.mlorenzo.springbootkafkademo.service.JsonMessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/kafka")
public class JsonMessageController {

    private final JsonMessageService messageService;

    public JsonMessageController(JsonMessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/publish")
    public String publish(@RequestBody User user) {
        messageService.sendMessage(user);
        return "Json Message send to the topic";
    }
}
