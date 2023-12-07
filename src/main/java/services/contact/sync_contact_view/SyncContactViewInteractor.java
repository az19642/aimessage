package services.contact.sync_contact_view;

import entities.Contact;
import entities.Message;
import entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Interactor responsible for handling the input of the use case.
 */
public class SyncContactViewInteractor implements SyncContactViewInputBoundary {
    final SyncContactViewDataAccessInterface syncContactViewDataAccessObject;
    final SyncContactViewOutputBoundary syncContactViewPresenter;

    public SyncContactViewInteractor(SyncContactViewOutputBoundary syncContactViewPresenter,
                                     SyncContactViewDataAccessInterface syncContactViewDataAccessObject) {
        this.syncContactViewDataAccessObject = syncContactViewDataAccessObject;
        this.syncContactViewPresenter = syncContactViewPresenter;
    }

    /**
     * This method is called by the controller to execute the use case.
     */

    @Override
    public void execute() {
        Map<String, String> contactToMessage = new HashMap<>();
        syncContactViewDataAccessObject.syncUser();
        User currentUser = syncContactViewDataAccessObject.getUser();
        String lastMessage = "";
        for (Contact contact : currentUser.getContacts()) {
            List<Message> messageList = contact.getMessages();
            if (!messageList.isEmpty()) {
                lastMessage = messageList.get(messageList.size() - 1).getContent();
            }
            contactToMessage.put(contact.getName(), lastMessage);
        }
        SyncContactViewOutputData syncContactViewOutputData = new SyncContactViewOutputData(contactToMessage);
        syncContactViewPresenter.prepareSuccessView(syncContactViewOutputData);
    }
}