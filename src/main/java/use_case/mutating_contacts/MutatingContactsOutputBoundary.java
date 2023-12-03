package use_case.mutating_contacts;

import use_case.login.LoginOutputData;

/**
 * Interface representing the output boundary for the mutating contacts use case.
 */
public interface MutatingContactsOutputBoundary {

    /**
     * Prepares the view for a successful mutating of a contact by providing the necessary data.
     *
     * Should just call teh main view to refresh the contacts
     */
    void prepareSuccessView();

    /**
     * Prepares the view for a failed mutation of contacts by providing an error message.
     * This will occur when the contacted added is not an existing user or they are already a contact to the user.
     *
     * @param error The error message indicating the reason for the login failure.
     */
    void prepareFailView(String error);

}
