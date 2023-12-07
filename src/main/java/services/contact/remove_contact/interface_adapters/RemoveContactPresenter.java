package services.contact.remove_contact.interface_adapters;


import services.contact.remove_contact.RemoveContactOutputBoundary;
import services.logged_in.LoggedInState;
import services.logged_in.LoggedInViewModel;

/**
 * Presenter responsible for handling the output of the mutating contacts
 * use case and updating the corresponding ViewModel.
 */

public class RemoveContactPresenter implements RemoveContactOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;

    public RemoveContactPresenter(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
    }

    /**
     * Prepares the view for a successful mutating of a contact by providing the necessary data.
     * <p>
     * Should just call the main view to refresh the contacts
     */
    @Override
    public void prepareSuccessView() {
        LoggedInState state = loggedInViewModel.getState();
        state.setMutatingContactsStatus("PASS");
        loggedInViewModel.setState(state);
        loggedInViewModel.firePropertyChanged();
    }

    /**
     * Prepares the view for a failed mutation of contacts by providing an error message.
     * This will occur when the contacted added is not an existing user or they are already a contact to the user.
     *
     * @param error The error message indicating the reason for the login failure.
     */
    @Override
    public void prepareFailView(String error) {
        LoggedInState state = loggedInViewModel.getState();
        state.setMutatingContactsStatus(error);
        loggedInViewModel.setState(state);
        loggedInViewModel.firePropertyChanged();
    }
}
