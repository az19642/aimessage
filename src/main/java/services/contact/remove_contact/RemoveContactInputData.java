package services.contact.remove_contact;

public class RemoveContactInputData {
    final private String contactName;

    public RemoveContactInputData(String contactName) {
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
}
