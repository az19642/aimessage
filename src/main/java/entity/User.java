package entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a user in the system with basic information and contacts.
 */
public interface User {

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    String getName();

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    String getPassword();

    /**
     * Gets the preferred language of the user.
     *
     * @return The preferred language of the user.
     */
    String getPreferredLanguage();

    /**
     * Gets the list of contacts associated with the user.
     *
     * @return The list of contacts associated with the user.
     */
    List<Contact> getContacts();

    /**
     * Returns a contact given the contact name
     * If not a contact returns null
     *
     * @param contactName the name of the contact
     * @return the contact entity with the given contact name
     */
    Contact getContact(String contactName);


    /**
     * Gets the timestamp when the user was created.
     *
     * @return The timestamp of the user creation.
     */
    LocalDateTime getCreationTime();
}