package app;

import data_access.MongoUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.load_contacts_to_view.LoadContactsToViewController;
import interface_adapter.load_contacts_to_view.LoadContactsToViewPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.mutating_contacts.MutatingContactsController;
import interface_adapter.mutating_contacts.MutatingContactsPresenter;
import use_case.load_contacts_to_view.LoadContactsToViewDataAccessInterface;
import use_case.load_contacts_to_view.LoadContactsToViewInputBoundary;
import use_case.load_contacts_to_view.LoadContactsToViewInteractor;
import use_case.load_contacts_to_view.LoadContactsToViewOutputBoundary;
import use_case.mutating_contacts.MutatingContactsInputBoundary;
import use_case.mutating_contacts.MutatingContactsInteractor;
import use_case.mutating_contacts.MutatingContactsOutputBoundary;
import use_case.mutating_contacts.MutatingContactsUserDataAccessInterface;
import view.LoggedInView;

/**
 * Factory responsible for creating the LoggedInView.
 */
public class LoggedInViewFactory {

    public static LoggedInView create(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel,
                                      MongoUserDataAccessObject mongoDataAccessObject) {

        LoadContactsToViewController loadContactsToViewController =
                createLoadContactsToViewController(loggedInViewModel, mongoDataAccessObject);
        MutatingContactsController mutatingContactsController = createMutatingContactsController(loggedInViewModel, mongoDataAccessObject);
        return new LoggedInView(loggedInViewModel, viewManagerModel, loadContactsToViewController,
                mutatingContactsController);

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

    /**
     * Creates the controller for the MutatingContacts use case.
     *
     * @param mongoDataAccessObject the data access object to be used by the controller.
     * @return the controller for the MutatingContacts use case.
     */
    private static MutatingContactsController createMutatingContactsController(LoggedInViewModel loggedInViewModel, MutatingContactsUserDataAccessInterface mongoDataAccessObject) {


        MutatingContactsOutputBoundary mutatingContactsPresenter =
                new MutatingContactsPresenter(loggedInViewModel);
        MutatingContactsInputBoundary mutatingContactsInteractor =
                new MutatingContactsInteractor(mongoDataAccessObject, mutatingContactsPresenter);

        return new MutatingContactsController(mutatingContactsInteractor);
    }
}
