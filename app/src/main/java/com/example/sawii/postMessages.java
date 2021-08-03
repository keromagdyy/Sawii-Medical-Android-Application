package com.example.sawii;

public class postMessages {
    private int chat_message_id;
    private int to_user_id;
    private int from_user_id;
    private String chat_message;
    private String timestamp;
    private int status;

    public postMessages(int to_user_id, int from_user_id, String chat_message, String timestamp, int status) {
        this.to_user_id = to_user_id;
        this.from_user_id = from_user_id;
        this.chat_message = chat_message;
        this.timestamp = timestamp;
        this.status = status;
    }

    public int getChat_message_id() {
        return chat_message_id;
    }

    public void setChat_message_id(int chat_message_id) {
        this.chat_message_id = chat_message_id;
    }

    public int getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    public int getFrom_user_id() {
        return from_user_id;
    }

    public void setFrom_user_id(int from_user_id) {
        this.from_user_id = from_user_id;
    }

    public String getChat_message() {
        return chat_message;
    }

    public void setChat_message(String chat_message) {
        this.chat_message = chat_message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
