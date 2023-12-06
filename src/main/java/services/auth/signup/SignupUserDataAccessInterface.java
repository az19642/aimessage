package services.auth.signup;

import entities.User;

/**
 * Interface for interacting with user data in the context of the signup use case.
 */
public interface SignupUserDataAccessInterface {
    boolean userExists(String identifier);

    void save(User user);
    void deleteUser(String message);
}