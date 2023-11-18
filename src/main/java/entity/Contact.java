package entity;

import java.time.LocalDateTime;
import java.util.List;


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

    /**
     * Retrieves the name associated with this contact.
     *
     * @return The name of this contact.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the last message time with this contact
     *
     * @return the time of the last message with this contact
     */
    public LocalDateTime getLastMessageTime() {
        return lastMessageTime;
    }

    /**
     * Retrieves the preferred language of the corresponding contact
     *
     * @return the preferred language of this contact
     */
    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    /**
     * Retrieves the messages sent between the user and the contact
     *
     * @return a List of messages between user and contact.
     */
    public List<Message> getMessages() {
        return messages;
    }
}