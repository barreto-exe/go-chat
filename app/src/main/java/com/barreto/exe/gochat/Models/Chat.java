package com.barreto.exe.gochat.models;

import java.io.Serializable;
import java.util.Date;

public class Chat implements Serializable {
    public Chat(String id, String name, String lastMessageUserName, String lastMessage, Date lastMessageDateTime) {
        this.id = id;
        this.name = name;
        this.lastMessageUserName = lastMessageUserName;
        this.lastMessage = lastMessage;
        this.lastMessageDateTime = lastMessageDateTime;
    }

    private String id, name, lastMessageUserName, lastMessage;
    private Date lastMessageDateTime;

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
    public String getLastMessageUserName() {
        return lastMessageUserName;
    }
    public void setLastMessageUserName(String lastMessageUserName) {
        this.lastMessageUserName = lastMessageUserName;
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
