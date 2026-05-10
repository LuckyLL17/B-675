package com.echolink.model;

/**
 * 消息数据模型
 */
public class Message {
    
    private String content;
    
    public Message() {
    }
    
    public Message(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
}
