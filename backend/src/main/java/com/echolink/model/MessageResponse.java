package com.echolink.model;

public class MessageResponse {

    private String content;
    private String timestamp;
    private boolean success;

    public MessageResponse() {
    }

    public MessageResponse(String content, String timestamp, boolean success) {
        this.content = content;
        this.timestamp = timestamp;
        this.success = success;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
