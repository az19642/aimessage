package app;

import interface_adapter.load_contacts_to_view.LoadContactsToViewController;
import interface_adapter.load_contacts_to_view.LoadContactsToViewPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.load_contacts_to_view.LoadContactsToViewDataAccessInterface;
import use_case.load_contacts_to_view.LoadContactsToViewInputBoundary;
import use_case.load_contacts_to_view.LoadContactsToViewInteractor;
import use_case.load_contacts_to_view.LoadContactsToViewOutputBoundary;
import view.LoggedInView;

/**
 * Factory responsible for creating the LoggedInView.
 */
public class LoadContactsToViewUseCaseFactory {

    public static LoggedInView create(LoggedInViewModel loggedInViewModel,
                                      LoadContactsToViewDataAccessInterface mongoDataAccessObject) {

        LoadContactsToViewController loadContactsToViewController =
                createLoadContactsToViewController(loggedInViewModel, mongoDataAccessObject);
        return new LoggedInView(loggedInViewModel, loadContactsToViewController);

    }

    /**
     * Creates the controller for the LoadContactsToView use case.
     *
     * @param loggedInViewModel     the ViewModel to be updated by the controller.
     * @param mongoDataAccessObject the data access object to be used by the controller.
     * @return the controller for the LoadContactsToView use case.
     */
    private static LoadContactsToViewController createLoadContactsToViewController(LoggedInViewModel loggedInViewModel, LoadContactsToViewDataAccessInterface mongoDataAccessObject) {


        LoadContactsToViewOutputBoundary loadContactsToViewPresenter =
                new LoadContactsToViewPresenter(loggedInViewModel);
        LoadContactsToViewInputBoundary loadContactsToViewInteractor =
                new LoadContactsToViewInteractor(loadContactsToViewPresenter, mongoDataAccessObject);

        return new LoadContactsToViewController(loadContactsToViewInteractor);
    }
}
