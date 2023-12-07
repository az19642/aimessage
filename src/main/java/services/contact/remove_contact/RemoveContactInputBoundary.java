package services.contact.remove_contact;

/**
 * Interface representing the input boundary for the mutating contacts use case.
 */
public interface RemoveContactInputBoundary {
    /**
     * Executes the mutating contacts use case with the provided mutating contacts input data.
     *
     * @param removeContactInputData The input data for the mutating contacts use case.
     */
    void execute(RemoveContactInputData removeContactInputData);
}
