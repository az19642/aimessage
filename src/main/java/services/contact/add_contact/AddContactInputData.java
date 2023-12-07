package services.contact.add_contact;

public class AddContactInputData {
    final private String contactName;

    public AddContactInputData(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Gets the contact name to add or delete.
     *
     * @return The contact name.
     */
    String getContactName() {
        return contactName;
    }

    /**
     * Gets whether to add or delete contact.
     *
     * @return True if adding contact, False to delete contact.
     */

}
