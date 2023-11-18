package use_case.signup;

import entity.User;

/**
 * Interface for interacting with user data in the context of the signup use case.
 */
public interface SignupUserDataAccessInterface {

    /**
     * Checks if a user with the given identifier already exists.
     *
     * @param identifier The identifier to check (e.g., username).
     * @return {@code true} if a user with the given identifier already exists; {@code false} otherwise.
     */
    boolean userExists(String identifier);

    /**
     * Saves a new user to the data storage.
     *
     * @param user The user to be saved.
     */
    void save(User user);
}