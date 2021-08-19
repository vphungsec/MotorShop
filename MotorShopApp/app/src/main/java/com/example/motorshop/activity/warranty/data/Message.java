package com.example.motorshop.activity.warranty.data;

public class Message {
    String message;
    String id;
    String time;

    public Message(){}

    public Message(String message, String id, String time) {
        this.message = message;
        this.id = id;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
