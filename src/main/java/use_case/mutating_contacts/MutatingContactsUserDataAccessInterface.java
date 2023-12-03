package use_case.mutating_contacts;

import entity.Contact;
import entity.User;

public interface MutatingContactsUserDataAccessInterface {

    /**
     * Adds a contact to the user, and will also update the contact such that the user will be a contact for them.
     * Precondition: The given username is a valid contact.
     * <p>
     * There are four cases for the state after this function returns
     * 1. Contact successfully added
     * 2. Contact does not exist in database
     * 3. Contact is already a contact to the user
     * 4. Unexpected error occurred
     *
     * @param user the current user
     * @return A string in {"PASS", "USER DNE", "ALREADY A CONTACT", "FAILED"}
     */
    String addContact(User user, String contactName);

    /**
     * Preconditions:
     * - The contact is in the database
     * - The contact is one of the users contacts
     * <p>
     * Deletes a contact of the user, and will also update the contact such that the user will be a contact for them.
     * Precondition: The given username is a valid contact.
     *
     * @param user          the current user
     * @param contactEntity the contacts entity
     */
    void deleteContact(User user, Contact contactEntity);

    /**
     * getter for user attribute
     * setUser should have been called once before this was called
     * @return the user attribute
     */
    User getUser();

}
