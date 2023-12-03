package interface_adapter.mutating_contacts;


import use_case.mutating_contacts.MutatingContactsOutputBoundary;

/**
 * Presenter responsible for handling the output of the mutating contacts
 * use case and updating the corresponding ViewModel.
 */

public class MutatingContactsPresenter implements MutatingContactsOutputBoundary {

    /**
     * Prepares the view for a successful mutating of a contact by providing the necessary data.
     *
     * Should just call teh main view to refresh the contacts
     */
    @Override
    public void prepareSuccessView() {

    }

    /**
     * Prepares the view for a failed mutation of contacts by providing an error message.
     * This will occur when the contacted added is not an existing user or they are already a contact to the user.
     *
     * @param error The error message indicating the reason for the login failure.
     */
    @Override
    public void prepareFailView(String error) {

    }
}
