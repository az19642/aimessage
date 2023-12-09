package entities;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class represents a common user.
 */
class CommonUser implements User {

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;
    private String preferredLanguage;
    private List<Contact> contacts;

    /**
     * Constructs a new CommonUser object.
     *
     * @param name              The name of the user.
     * @param password          The password of the user.
     * @param preferredLanguage The preferred language of the user.
     * @param creationTime      The creation time of the user.
     * @param contacts          The contacts of the user.
     */
    CommonUser(String name, String password, String preferredLanguage, LocalDateTime creationTime,
               List<Contact> contacts) {
        this.name = name;
        this.password = password;
        this.preferredLanguage = preferredLanguage;
        this.contacts = contacts;
        this.creationTime = creationTime;
    }

    /**
     * Retrieves the name associated with this user.
     *
     * @return The name of this user.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Retrieves the password associated with this user.
     *
     * @return The password of this user.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the preferred language of the user
     *
     * @return the preferred language of this user
     */
    @Override
    public String getPreferredLanguage() {
        return preferredLanguage;
    }


    /**
     * Retrieves the contacts associated with this user.
     *
     * @return A list of contacts of this user.
     */
    @Override
    public List<Contact> getContacts() {
        return contacts;
    }

    /**
     * Retrieves the contact with the given name.
     *
     * @param contactName The name of the contact to retrieve.
     * @return The contact with the given name.
     */
    @Override
    public Contact getContact(String contactName) {

        for (Contact contact : contacts) {
            if (contact.getName().equals(contactName)) {
                return contact;
            }
        }

        return null;
    }


    /**
     * Retrieves the creation time of this user.
     *
     * @return The LocalDateTime for the creation time of this user.
     */
    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}