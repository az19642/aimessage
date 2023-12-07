package services.conversation.view_sync;

import entities.Contact;
import entities.Message;
import entities.User;

import java.util.List;

public class ConversationSyncInteractor implements ConversationSyncInputBoundary {
    final ConversationSyncDataAccessInterface dataAccessObject;
    final ConversationSyncOutputBoundary presenter;

    public ConversationSyncInteractor(ConversationSyncDataAccessInterface dataAccessObject,
                                      ConversationSyncOutputBoundary presenter) {
        this.dataAccessObject = dataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(ConversationSyncInputData inputData) {
        User user = dataAccessObject.getUser();
        Contact contact = user.getContact(inputData.getContactName());
        List<Message> messages = contact.getMessages();
        ConversationSyncOutputData outputData = new ConversationSyncOutputData(messages);
        presenter.prepareSuccessView(outputData);
    }
}
