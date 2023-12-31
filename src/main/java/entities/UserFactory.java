package entities;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Factory interface responsible for creating instances of the {@link User} class.
 */
public interface UserFactory {

    /**
     * Requires: password is valid.
     */
    User create(String name, String password, String preferredLanguage, LocalDateTime creationTime,
                List<Contact> contacts);
}