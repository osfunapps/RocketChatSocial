package com.osapps.chat.model;

import com.stfalcon.chatkit.commons.models.IMessage;

import java.util.Date;

/*
 * Created by sachin76 zon 04.04.17.
 */

public class Message implements IMessage{

    private String id;
    private String text;
    private Date createdAt;
    private User user;

    public Message(String id, User user, String text) {
        this(id, user, text, new Date());
    }

    public Message(String id, User user, String text, Date createdAt) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Message (com.rocketchat.core.model.Message message) {
        id = message.id();
        text = message.message();
        createdAt = new Date(message.timestamp());
        user = new User(message.sender());
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                '}';
    }
}
