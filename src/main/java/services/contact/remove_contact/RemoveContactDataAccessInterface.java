package services.contact.remove_contact;

import entities.User;

public interface RemoveContactDataAccessInterface {
    /**
     * Preconditions:
     * - The contact is in the database
     * - The contact is one of the users contacts
     * <p>
     * Deletes a contact of the user, and will also update the contact such that the user will be a contact for them.
     * Precondition: The given username is a valid contact.
     *
     * @param contactName the contacts name
     */
    void deleteContact(String contactName);

    /**
     * getter for user attribute
     * setUser should have been called once before this was called
     *
     * @return the user attribute
     */
    User getUser();

}
