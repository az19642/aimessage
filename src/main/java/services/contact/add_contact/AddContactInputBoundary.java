package services.contact.add_contact;

/**
 * Interface representing the input boundary for the mutating contacts use case.
 */
public interface AddContactInputBoundary {
    /**
     * Executes the mutating contacts use case with the provided mutating contacts input data.
     *
     * @param addContactInputData The input data for the mutating contacts use case.
     */
    void execute(AddContactInputData addContactInputData);
}
