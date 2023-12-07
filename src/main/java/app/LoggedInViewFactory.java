package app;

import data_access.MongoDataAccessObject;
import interface_adapter.ViewManagerModel;
import services.contact_mutation.MutatingContactsInputBoundary;
import services.contact_mutation.MutatingContactsInteractor;
import services.contact_mutation.MutatingContactsOutputBoundary;
import services.contact_mutation.MutatingContactsUserDataAccessInterface;
import services.contact_mutation.interface_adapters.MutatingContactsController;
import services.contact_mutation.interface_adapters.MutatingContactsPresenter;
import services.logged_in.LoggedInViewModel;
import services.contacts_view_sync.LoadContactsToViewDataAccessInterface;
import services.contacts_view_sync.LoadContactsToViewInputBoundary;
import services.contacts_view_sync.LoadContactsToViewInteractor;
import services.contacts_view_sync.LoadContactsToViewOutputBoundary;
import services.contacts_view_sync.interface_adapters.LoadContactsToViewController;
import services.contacts_view_sync.interface_adapters.LoadContactsToViewPresenter;
import services.conversation.interface_adapters.ConversationViewModel;
import views.LoggedInView;

/**
 * Factory responsible for creating the LoggedInView.
 */
public class LoggedInViewFactory {

    public static LoggedInView create(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel,
                                      MongoDataAccessObject mongoDataAccessObject,
                                      ConversationViewModel conversationViewModel) {

        LoadContactsToViewController loadContactsToViewController =
                createLoadContactsToViewController(loggedInViewModel, mongoDataAccessObject);
        MutatingContactsController mutatingContactsController = createMutatingContactsController(loggedInViewModel,
                mongoDataAccessObject);
        return new LoggedInView(loggedInViewModel, viewManagerModel, loadContactsToViewController,
                mutatingContactsController, conversationViewModel);

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
    private static MutatingContactsController createMutatingContactsController(LoggedInViewModel loggedInViewModel,
                                                                               MutatingContactsUserDataAccessInterface mongoDataAccessObject) {


        MutatingContactsOutputBoundary mutatingContactsPresenter =
                new MutatingContactsPresenter(loggedInViewModel);
        MutatingContactsInputBoundary mutatingContactsInteractor =
                new MutatingContactsInteractor(mongoDataAccessObject, mutatingContactsPresenter);

        return new MutatingContactsController(mutatingContactsInteractor);
    }
}
