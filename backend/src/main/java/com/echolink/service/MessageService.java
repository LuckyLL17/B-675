package com.echolink.service;

import com.echolink.model.Message;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 消息业务逻辑服务
 */
@Service
public class MessageService {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * 处理消息并输出到控制台
     */
    public void processMessage(Message message) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        System.out.println("========================================");
        System.out.println("[" + timestamp + "] 收到新消息:");
        System.out.println("内容: " + message.getContent());
        System.out.println("========================================");
    }
}
