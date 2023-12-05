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
     */
    void setUser(String username, String password);

    /**
     * getter for user attribute
     * setUser should have been called once before this was called
     * @return the user attribute
     */
    User getUser();
}