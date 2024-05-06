package com.barreto.exe.gochat.models;

import java.io.Serializable;
import java.util.Date;

public class Chat implements Serializable {

    public Chat(String id, String creatorId, String name, String lastMessageUsername, String lastMessage, Date lastMessageDateTime) {
        this.id = id;
        this.creatorId = creatorId;
        this.name = name;
        this.lastMessageUsername = lastMessageUsername;
        this.lastMessage = lastMessage;
        this.lastMessageDateTime = lastMessageDateTime;
    }

    private String id, creatorId, name, lastMessageUsername, lastMessage;
    private Date lastMessageDateTime;

    public String getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastMessageUsername() {
        return lastMessageUsername;
    }
    public void setLastMessageUsername(String lastMessageUsername) {
        this.lastMessageUsername = lastMessageUsername;
    }

    public String getLastMessage() {
        return lastMessage;
    }
    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
    public Date getLastMessageDateTime() {
        return lastMessageDateTime;
    }
    public void setLastMessageDateTime(Date lastMessageDateTime) {
        this.lastMessageDateTime = lastMessageDateTime;
    }
}
