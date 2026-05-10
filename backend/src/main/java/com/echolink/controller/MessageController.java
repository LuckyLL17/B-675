package com.echolink.controller;

import com.echolink.model.Message;
import com.echolink.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息 REST API 控制器
 */
@RestController
@RequestMapping("/api")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 接收消息
     * POST /api/message
     */
    @PostMapping("/message")
    public ResponseEntity<Map<String, Object>> receiveMessage(@RequestBody Message message) {
        messageService.processMessage(message);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "消息接收成功");

        return ResponseEntity.ok(response);
    }

    /**
     * 健康检查
     * GET /api/health
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        return ResponseEntity.ok(response);
    }
}
