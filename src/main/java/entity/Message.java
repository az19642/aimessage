package entity;

import java.time.LocalDateTime;

/**
 * This class represents a message between a user and contact
 */
public class Message {
    private final String content;
    private final String sender;
    private final LocalDateTime messageTime;

    Message(String content, String sender) {
        this.content = content;
        this.sender = sender;
        messageTime = LocalDateTime.now();
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    public String getSender() {
        return sender;
    }
}
