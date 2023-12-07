package services.conversation.view_sync;

import entities.Contact;
import entities.Message;
import entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ConversationSyncInteractor implements ConversationSyncInputBoundary {
    final ConversationSyncDataAccessInterface conversationSyncDataAccessObject;
    final ConversationSyncOutputBoundary conversationSyncPresenter;

    public ConversationSyncInteractor(ConversationSyncDataAccessInterface conversationSyncDataAccessObject,
                                      ConversationSyncOutputBoundary conversationSyncPresenter) {
        this.conversationSyncDataAccessObject = conversationSyncDataAccessObject;
        this.conversationSyncPresenter = conversationSyncPresenter;
    }

    @Override
    public void execute(ConversationSyncInputData conversationSyncInputData) {
        User currentUser = conversationSyncDataAccessObject.getUser();
        Contact contact = currentUser.getContact(conversationSyncInputData.getContactName());
        List<Message> messages = contact.getMessages();

        Map<LocalDateTime, List<String>> timestampToMessage = new TreeMap<>();

        for (Message message: messages) {
            ArrayList<String> messageData = new ArrayList<>();
            messageData.add(message.getSender()); messageData.add(message.getContent());
            timestampToMessage.put(message.getMessageTime(), messageData);
        }

        ConversationSyncOutputData outputData = new ConversationSyncOutputData(timestampToMessage);
        conversationSyncPresenter.prepareSuccessView(outputData);
    }
}
