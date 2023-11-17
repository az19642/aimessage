package entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class is a factory class for the entity class
 */
public class ContactFactory {

    /**
     * @param name              the contact's name
     * @param preferredLanguage the contact's preferred language
     * @param lastMessageTime   the time of the last message between the contact and user
     * @param messages          the list of messages between the contact and user
     * @return an instance of the contact class
     */
    public Contact create(String name, String preferredLanguage, LocalDateTime lastMessageTime,
                          List<Message> messages) {
        return new Contact(name, preferredLanguage, lastMessageTime, messages);
    }
}
