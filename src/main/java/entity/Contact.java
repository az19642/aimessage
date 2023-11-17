package entity;

import java.util.List;
import java.time.LocalDateTime;


/**
 * Represents a contact to a user in the software
 * Notice that the lastMessageTime is used to sort the contacts of the user
 */

public class Contact {

    private final String name;
    private String preferredLanguage;
    private LocalDateTime lastMessageTime;
    private List<Message> messages;

    Contact(String name, String preferredLanguage, LocalDateTime lastMessageTime, List<Message> messages) {
        this.name = name;
        this.preferredLanguage = preferredLanguage;
        this.lastMessageTime = lastMessageTime;
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getLastMessageTime() {
        return lastMessageTime;
    }
    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
