package com.barreto.exe.gochat.models;

import java.util.Date;

public class Message {
    private String id, senderId, senderUsername, content;
    private Date dateTime;

    public Message(String id, String senderId, String senderUsername, String content, Date dateTime) {
        this.id = id;
        this.senderId = senderId;
        this.senderUsername = senderUsername;
        this.content = content;
        this.dateTime = dateTime;
    }

    public Message(String senderId, String senderUsername, String content, Date dateTime) {
        this.senderId = senderId;
        this.senderUsername = senderUsername;
        this.content = content;
        this.dateTime = dateTime;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
