package services.contact.add_contact.interface_adapters;


import services.logged_in.LoggedInState;
import services.logged_in.LoggedInViewModel;
import services.contact.add_contact.AddContactOutputBoundary;

/**
 * Presenter responsible for handling the output of the mutating contacts use case.
 */

public class AddContactPresenter implements AddContactOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;

    public AddContactPresenter(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
    }

    /**
     * Prepares the view for a successful mutation of contacts by providing a success message.
     *
     */
    @Override
    public void prepareSuccessView() {
        LoggedInState state = loggedInViewModel.getState();
        state.setMutatingContactsStatus("PASS");
        loggedInViewModel.setState(state);
        loggedInViewModel.firePropertyChanged();
    }

    /**
     * Prepares the view for a failed mutation of contacts by providing an appropriate error message.
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
