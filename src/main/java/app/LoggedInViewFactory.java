package app;

import data_access.MongoUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import features.view_data_sync.update_contacts.interface_adapters.LoadContactsToViewController;
import features.view_data_sync.update_contacts.interface_adapters.LoadContactsToViewPresenter;
import features.logged_in.LoggedInViewModel;
import features.contact_mutation.interface_adapters.MutatingContactsController;
import features.contact_mutation.interface_adapters.MutatingContactsPresenter;
import features.view_data_sync.update_contacts.LoadContactsToViewDataAccessInterface;
import features.view_data_sync.update_contacts.LoadContactsToViewInputBoundary;
import features.view_data_sync.update_contacts.LoadContactsToViewInteractor;
import features.view_data_sync.update_contacts.LoadContactsToViewOutputBoundary;
import features.contact_mutation.MutatingContactsInputBoundary;
import features.contact_mutation.MutatingContactsInteractor;
import features.contact_mutation.MutatingContactsOutputBoundary;
import features.contact_mutation.MutatingContactsUserDataAccessInterface;
import views.LoggedInView;

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
