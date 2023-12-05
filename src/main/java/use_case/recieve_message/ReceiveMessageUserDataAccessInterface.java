package use_case.recieve_message;

import entity.User;

public interface ReceiveMessageUserDataAccessInterface {

    /**
     * This method should be called when the user would like to log in
     * If the username and password do not match a user, or the username is not valid null will be returned
     * Otherwise it will return the user with the given username.
     *
     * @param name     the username of the user
     * @param password the password of the user
     */
    void setUser(String name, String password);

    /**
     * getter for user attribute
     * setUser should have been called once before this was called
     * @return the user attribute
     */
    User getUser();

}
