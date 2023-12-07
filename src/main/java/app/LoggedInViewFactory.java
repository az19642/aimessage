package app;

import interface_adapter.ViewManagerModel;
import services.contact.add_contact.AddContactDataAccessInterface;
import services.contact.add_contact.AddContactInputBoundary;
import services.contact.add_contact.AddContactInteractor;
import services.contact.add_contact.AddContactOutputBoundary;
import services.contact.add_contact.interface_adapters.AddContactController;
import services.contact.add_contact.interface_adapters.AddContactPresenter;
import services.contact.remove_contact.RemoveContactDataAccessInterface;
import services.contact.remove_contact.RemoveContactInputBoundary;
import services.contact.remove_contact.RemoveContactInteractor;
import services.contact.remove_contact.RemoveContactOutputBoundary;
import services.contact.remove_contact.interface_adapters.RemoveContactController;
import services.contact.remove_contact.interface_adapters.RemoveContactPresenter;
import services.contact.sync_view.LoadContactsToViewDataAccessInterface;
import services.contact.sync_view.LoadContactsToViewInputBoundary;
import services.contact.sync_view.LoadContactsToViewInteractor;
import services.contact.sync_view.LoadContactsToViewOutputBoundary;
import services.contact.sync_view.interface_adapters.LoadContactsToViewController;
import services.contact.sync_view.interface_adapters.LoadContactsToViewPresenter;
import services.conversation.interface_adapters.ConversationViewModel;
import services.logged_in.LoggedInViewModel;
import views.LoggedInView;

/**
 * Factory responsible for creating the LoggedInView.
 */
public class LoggedInViewFactory {

    public static LoggedInView create(ViewManagerModel viewManagerModel,
                                      LoggedInViewModel loggedInViewModel,
                                      ConversationViewModel conversationViewModel,
                                      LoadContactsToViewDataAccessInterface loadContactsToViewMongoDataAccessObject,
                                      AddContactDataAccessInterface addContactMongoDataAccessObject,
                                      RemoveContactDataAccessInterface removeContactMongoDataAccessObject) {

        LoadContactsToViewController loadContactsToViewController =
                createLoadContactsToViewController(loggedInViewModel, loadContactsToViewMongoDataAccessObject);

        RemoveContactController removeContactController = createRemoveContactController(loggedInViewModel,
                removeContactMongoDataAccessObject);

        AddContactController addContactController = createAddContactController(loggedInViewModel,
                addContactMongoDataAccessObject);

        return new LoggedInView(loggedInViewModel, viewManagerModel, loadContactsToViewController,
                addContactController, removeContactController, conversationViewModel);

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
    private static AddContactController createAddContactController(LoggedInViewModel loggedInViewModel,
                                                                   AddContactDataAccessInterface mongoDataAccessObject) {


        AddContactOutputBoundary addContactPresenter =
                new AddContactPresenter(loggedInViewModel);
        AddContactInputBoundary addContactInteractor =
                new AddContactInteractor(mongoDataAccessObject, addContactPresenter);

        return new AddContactController(addContactInteractor);
    }

    private static RemoveContactController createRemoveContactController(LoggedInViewModel loggedInViewModel,
                                                                         RemoveContactDataAccessInterface mongoDataAccessObject) {


        RemoveContactOutputBoundary removeContactPresenter =
                new RemoveContactPresenter(loggedInViewModel);
        RemoveContactInputBoundary removeContactInteractor =
                new RemoveContactInteractor(mongoDataAccessObject, removeContactPresenter);

        return new RemoveContactController(removeContactInteractor);
    }
}
