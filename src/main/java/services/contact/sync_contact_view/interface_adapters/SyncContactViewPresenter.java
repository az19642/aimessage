package services.contact.sync_contact_view.interface_adapters;

import services.contact.sync_contact_view.SyncContactViewOutputBoundary;
import services.contact.sync_contact_view.SyncContactViewOutputData;
import services.logged_in.interface_adapters.LoggedInState;
import services.logged_in.interface_adapters.LoggedInViewModel;

/**
 * Presenter responsible for handling the output of the use case and updating the corresponding ViewModel.
 */
public class SyncContactViewPresenter implements SyncContactViewOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;

    /**
     * Constructor.
     *
     * @param loggedInViewModel the ViewModel to be updated.
     */
    public SyncContactViewPresenter(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
    }

    /**
     * Updates the ViewModel with the data from the use case.
     *
     * @param syncContactViewOutputData the output data from the use case.
     */
    @Override
    public void prepareSuccessView(SyncContactViewOutputData syncContactViewOutputData) {
        LoggedInState currentState = loggedInViewModel.getState();
        currentState.setContactToLastMessage(syncContactViewOutputData.getContactToLastMessage());
        loggedInViewModel.setState(currentState);
        loggedInViewModel.firePropertyChanged();
    }


    /**
     * Will never be called; may be removed in the future.
     */
    @Override
    public void prepareFailView() {
        // will not be called
    }
}