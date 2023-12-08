package services.signup;

import entities.User;

/**
 * Interface for interacting with user data in the context of the signup use case.
 */
public interface SignupDataAccessInterface {
    boolean userExists(String identifier);

    void save(User user);

    void deleteUser(String message);
}