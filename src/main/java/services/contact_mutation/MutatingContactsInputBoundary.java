package services.contact_mutation;

/**
 * Interface representing the input boundary for the mutating contacts use case.
 */
public interface MutatingContactsInputBoundary {
    /**
     * Executes the mutating contacts use case with the provided mutating contacts input data.
     *
     * @param mutatingContactsInputData The input data for the mutating contacts use case.
     */
    void execute(MutatingContactsInputData mutatingContactsInputData);
}
