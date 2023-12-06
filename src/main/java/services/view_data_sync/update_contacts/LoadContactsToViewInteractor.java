package services.view_data_sync.update_contacts;

import entities.Contact;
import entities.Message;
import entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Interactor responsible for handling the input of the use case.
 */
public class LoadContactsToViewInteractor implements LoadContactsToViewInputBoundary {
    final LoadContactsToViewDataAccessInterface userDataAccessObject;
    final LoadContactsToViewOutputBoundary updateLoggedInStatePresenter;

    public LoadContactsToViewInteractor(LoadContactsToViewOutputBoundary updateLoggedInStatePresenter,
                                        LoadContactsToViewDataAccessInterface dataAccessObject) {
        this.userDataAccessObject = dataAccessObject;
        this.updateLoggedInStatePresenter = updateLoggedInStatePresenter;
    }

    /**
     * This method is called by the controller to execute the use case.
     */

    @Override
    public void execute() {
        Map<String, String> contactToMessage = new HashMap<>();
        User currentUser = userDataAccessObject.getUser();
        String lastMessage = "";
        for (Contact contact : currentUser.getContacts()) {
            List<Message> messageList = contact.getMessages();
            if (!messageList.isEmpty()) {
                lastMessage = messageList.get(messageList.size() - 1).getContent();
            }
            contactToMessage.put(contact.getName(), lastMessage);
        }
        LoadContactsToViewOutputData loadContactsToViewOutputData = new LoadContactsToViewOutputData(contactToMessage);
        updateLoggedInStatePresenter.prepareSuccessView(loadContactsToViewOutputData);
    }
}