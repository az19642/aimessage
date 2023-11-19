package use_case.signup;

import entity.User;

/**
 * Interface for interacting with user data in the context of the signup use case.
 */
public interface SignupUserDataAccessInterface {
    boolean userExists(String identifier);

    void save(User user);
}