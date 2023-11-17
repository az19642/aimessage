package entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


/**
 * This class is a factory class for the common user class
 */
public class CommonUserFactory implements UserFactory {

    /**
     *
     * Requires: password is valid.
     *
     * @param name the username of the user
     * @param password the users password
     * @param preferredLanguage the users preferred language
     * @param creationTime the time the users account was created
     * @param contacts the contacts associated with the user
     * @return An instance of the user entity
     */
    @Override
    public User create(String name, String password, String preferredLanguage, LocalDateTime creationTime,
                       List<Contact> contacts) {
        return new CommonUser(name, password, preferredLanguage, creationTime, contacts);
    }
}
