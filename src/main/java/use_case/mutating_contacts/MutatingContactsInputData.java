package use_case.mutating_contacts;

public class MutatingContactsInputData {
    final private String contactName;
    final private Boolean addContact;

    public MutatingContactsInputData(String contactName, Boolean addContact) {
        this.contactName = contactName;
        this.addContact = addContact;
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
    Boolean getAddContact() {
        return addContact;
    }

}
