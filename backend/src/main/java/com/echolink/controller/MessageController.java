package com.echolink.controller;

import com.echolink.common.ApiResponse;
import com.echolink.common.ResultUtils;
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
    public ResponseEntity<ApiResponse<Void>> receiveMessage(@RequestBody Message message) {
        messageService.processMessage(message);
        return ResponseEntity.ok(ResultUtils.success("消息接收成功", null));
    }

    /**
     * 健康检查
     * GET /api/health
     */
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Map<String, String>>> health() {
        Map<String, String> data = new HashMap<>();
        data.put("status", "UP");
        return ResponseEntity.ok(ResultUtils.success(data));
    }
}
