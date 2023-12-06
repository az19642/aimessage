package services.messaging.send_message;

import entities.User;

public interface SendMessageUserDataAccessInterface {

    /**
     * Creates a new message entry in the database between the user and the given contact
     * Also updates the entities
     * Precondition: The given contact name is a valid contact for the user.
     *
     * @param contactName the contacts name
     * @param messageContent the content of the message to be sent
     */
    void sendMessage(String contactName, String messageContent);

    /**
     * getter for user attribute
     * setUser should have been called once before this was called
     * @return the user attribute
     */
    User getUser();


}
