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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    @Override
    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
