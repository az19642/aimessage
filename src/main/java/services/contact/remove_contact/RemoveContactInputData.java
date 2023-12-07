package services.contact.remove_contact;

public class RemoveContactInputData {
    final private String contactName;

    public RemoveContactInputData(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Gets the contact name for remove contact.
     *
     * @return The contact name for remove contact.
     */
    String getContactName() {
        return contactName;
    }
}
