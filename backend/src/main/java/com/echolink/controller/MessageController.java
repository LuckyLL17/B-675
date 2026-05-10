package com.echolink.controller;

import com.echolink.common.Result;
import com.echolink.model.Message;
import com.echolink.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message")
    public ResponseEntity<Result<Void>> receiveMessage(@RequestBody Message message) {
        messageService.processMessage(message);
        return ResponseEntity.ok(Result.success("消息接收成功", null));
    }

    @GetMapping("/health")
    public ResponseEntity<Result<String>> health() {
        return ResponseEntity.ok(Result.success("UP"));
    }
}
