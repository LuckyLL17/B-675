package com.echolink.controller;

import com.echolink.common.ApiResponse;
import com.echolink.common.ResultCode;
import com.echolink.common.ResponseUtils;
import com.echolink.exception.BusinessException;
import com.echolink.model.Message;
import com.echolink.model.MessageResponse;
import com.echolink.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponse<MessageResponse>> receiveMessage(@RequestBody Message message) {
        if (message.getContent() == null || message.getContent().trim().isEmpty()) {
            throw new BusinessException(ResultCode.PARAM_VALID_ERROR, "消息内容不能为空");
        }

        MessageResponse response = messageService.processMessage(message);
        return ResponseEntity.ok(ResponseUtils.success("消息接收成功", response));
    }

    /**
     * 健康检查
     * GET /api/health
     */
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> health() {
        return ResponseEntity.ok(ResponseUtils.success("服务运行正常"));
    }
}
