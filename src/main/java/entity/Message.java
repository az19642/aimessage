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

    /**
     * Gets the content of the message.
     *
     * @return The content of the message.
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the timestamp when the message was sent.
     *
     * @return The timestamp of the message.
     */
    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    /**
     * Gets the sender of the message.
     *
     * @return The sender of the message.
     */
    public String getSender() {
        return sender;
    }
}