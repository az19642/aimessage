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
import services.contact.sync_contact_view.SyncContactViewDataAccessInterface;
import services.contact.sync_contact_view.SyncContactViewInputBoundary;
import services.contact.sync_contact_view.SyncContactViewInteractor;
import services.contact.sync_contact_view.SyncContactViewOutputBoundary;
import services.contact.sync_contact_view.interface_adapters.SyncContactViewController;
import services.contact.sync_contact_view.interface_adapters.SyncContactViewPresenter;
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
                                      SyncContactViewDataAccessInterface loadContactsToViewMongoDataAccessObject,
                                      AddContactDataAccessInterface addContactMongoDataAccessObject,
                                      RemoveContactDataAccessInterface removeContactMongoDataAccessObject) {

        SyncContactViewController syncContactViewController =
                createLoadContactsToViewController(loggedInViewModel, loadContactsToViewMongoDataAccessObject);

        RemoveContactController removeContactController = createRemoveContactController(loggedInViewModel,
                removeContactMongoDataAccessObject);

        AddContactController addContactController = createAddContactController(loggedInViewModel,
                addContactMongoDataAccessObject);

        return new LoggedInView(loggedInViewModel, viewManagerModel, syncContactViewController,
                addContactController, removeContactController, conversationViewModel);

    }

    /**
     * Creates the controller for the LoadContactsToView use case.
     *
     * @param loggedInViewModel     the ViewModel to be updated by the controller.
     * @param mongoDataAccessObject the data access object to be used by the controller.
     * @return the controller for the LoadContactsToView use case.
     */
    private static SyncContactViewController createLoadContactsToViewController(LoggedInViewModel loggedInViewModel, SyncContactViewDataAccessInterface mongoDataAccessObject) {


        SyncContactViewOutputBoundary loadContactsToViewPresenter =
                new SyncContactViewPresenter(loggedInViewModel);
        SyncContactViewInputBoundary loadContactsToViewInteractor =
                new SyncContactViewInteractor(loadContactsToViewPresenter, mongoDataAccessObject);

        return new SyncContactViewController(loadContactsToViewInteractor);
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
