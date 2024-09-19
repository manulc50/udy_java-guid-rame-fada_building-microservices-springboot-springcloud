package net.mlorenzo.springbootkafkademo.controller;

import net.mlorenzo.springbootkafkademo.service.StringMessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/kafka")
public class MessageController {

    private final StringMessageService messageService;

    public MessageController(StringMessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("publish")
    public String publish(@RequestParam String message) {
        messageService.sendMessage(message);
        return "Message send to the topic";
    }
}
