package use_case.login;

import entity.User;

/**
 * Interface representing the data access methods for the login use case.
 */
public interface LoginUserDataAccessInterface {

    /**
     * Retrieves a user with the specified username and password.
     *
     * @param username The username of the user to retrieve.
     * @param password The password of the user to retrieve.
     * @return The user with the specified username and password, or null if not found.
     */
    User getUser(String username, String password);
}