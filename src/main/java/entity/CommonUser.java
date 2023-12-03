package entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class represents a common user of the software
 * Every user will have a unique username
 */
class CommonUser implements User {

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;
    private String preferredLanguage;
    private List<Contact> contacts;

    CommonUser(String name, String password, String preferredLanguage,
               LocalDateTime creationTime, List<Contact> contacts) {
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
     * Retrieves the List of contacts associated with this user.
     *
     * @return the List of contacts of this user
     */
    @Override
    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public Contact getContact(String contactName) {

        for(Contact contact: contacts) {
            if (contact.getName().equals(contactName)) {
                return contact;
            }
        }

        return null;
    }

    /**
     * Retrieves the creation time associated with this object.
     *
     * @return the creation time of the object
     */
    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}