package app;

import interface_adapters.ViewManagerModel;
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
import services.logged_in.interface_adapters.LoggedInViewModel;
import views.LoggedInView;

/**
 * Factory for creating the logged in view.
 */
public class LoggedInViewFactory {

    private LoggedInViewFactory() {
    }

    /**
     * Creates a new instance of the logged in view.
     *
     * @param viewManagerModel                   The view manager model.
     * @param loggedInViewModel                  The logged in view model.
     * @param conversationViewModel              The conversation view model.
     * @param syncContactViewDataAccessInterface The data access object for syncing contacts.
     * @param addContactDataAccessInterface      The data access object for adding contacts.
     * @param removeContactDataAccessInterface   The data access object for removing contacts.
     * @return The logged in view.
     */
    public static LoggedInView create(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel,
                                      ConversationViewModel conversationViewModel,
                                      SyncContactViewDataAccessInterface syncContactViewDataAccessInterface,
                                      AddContactDataAccessInterface addContactDataAccessInterface,
                                      RemoveContactDataAccessInterface removeContactDataAccessInterface) {

        SyncContactViewController syncContactViewController = createLoadContactsToViewController(loggedInViewModel,
                syncContactViewDataAccessInterface);

        RemoveContactController removeContactController = createRemoveContactController(loggedInViewModel,
                removeContactDataAccessInterface);

        AddContactController addContactController = createAddContactController(loggedInViewModel,
                addContactDataAccessInterface);

        return LoggedInView.getInstance(loggedInViewModel, viewManagerModel, syncContactViewController,
                addContactController, removeContactController, conversationViewModel);

    }

    private static SyncContactViewController createLoadContactsToViewController(LoggedInViewModel loggedInViewModel,
                                                                                SyncContactViewDataAccessInterface syncContactViewDataAccessInterface) {

        SyncContactViewOutputBoundary loadContactsToViewPresenter = new SyncContactViewPresenter(loggedInViewModel);
        SyncContactViewInputBoundary loadContactsToViewInteractor = new SyncContactViewInteractor(
                loadContactsToViewPresenter, syncContactViewDataAccessInterface);

        return new SyncContactViewController(loadContactsToViewInteractor);
    }

    private static AddContactController createAddContactController(LoggedInViewModel loggedInViewModel,
                                                                   AddContactDataAccessInterface addContactDataAccessInterface) {

        AddContactOutputBoundary addContactPresenter = new AddContactPresenter(loggedInViewModel);
        AddContactInputBoundary addContactInteractor = new AddContactInteractor(addContactDataAccessInterface,
                addContactPresenter);

        return new AddContactController(addContactInteractor);
    }

    private static RemoveContactController createRemoveContactController(LoggedInViewModel loggedInViewModel,
                                                                         RemoveContactDataAccessInterface removeContactDataAccessInterface) {

        RemoveContactOutputBoundary removeContactPresenter = new RemoveContactPresenter(loggedInViewModel);
        RemoveContactInputBoundary removeContactInteractor = new RemoveContactInteractor(
                removeContactDataAccessInterface, removeContactPresenter);

        return new RemoveContactController(removeContactInteractor);
    }
}
