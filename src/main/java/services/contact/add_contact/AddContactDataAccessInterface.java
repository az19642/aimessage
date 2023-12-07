package services.contact.add_contact;

import entities.User;

public interface AddContactDataAccessInterface {

    /**
     * Adds a contact to the user's contact list.
     *
     * @param contactName The name of the contact to add.
     * @return A message indicating whether the contact was added successfully.
     */
    String addContact(String contactName);

    /**
     * Gets the current user.
     *
     * @return The current user.
     */
    User getUser();

}
