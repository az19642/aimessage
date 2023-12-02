package interface_adapter.load_contacts_to_view;

import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.load_contacts_to_view.LoadContactsToViewOutputBoundary;
import use_case.load_contacts_to_view.LoadContactsToViewOutputData;

/**
 * Presenter responsible for handling the output of the use case and updating the corresponding ViewModel.
 */
public class LoadContactsToViewPresenter implements LoadContactsToViewOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;

    /**
     * Constructor.
     *
     * @param loggedInViewModel the ViewModel to be updated.
     */
    public LoadContactsToViewPresenter(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
    }

    /**
     * Updates the ViewModel with the data from the use case.
     *
     * @param loadContactsToViewOutputData the output data from the use case.
     */
    @Override
    public void prepareSuccessView(LoadContactsToViewOutputData loadContactsToViewOutputData) {
        LoggedInState currentState = loggedInViewModel.getState();
        currentState.setContactToLastMessage(loadContactsToViewOutputData.getContactToLastMessage());
        loggedInViewModel.setState(currentState);
        loggedInViewModel.firePropertyChanged();
    }


    /**
     * Will never be called; may be removed in the future.
     */
    @Override
    public void prepareFailView() {
        // will never be called
    }
}