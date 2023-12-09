package services.contact.add_contact;

/**
 * Interface representing the input boundary for the add contact use case.
 */
public interface AddContactInputBoundary {
    /**
     * Executes the add contact use case with the provided input data.
     *
     * @param addContactInputData The input data for the add contact use case.
     */
    void execute(AddContactInputData addContactInputData);
}
