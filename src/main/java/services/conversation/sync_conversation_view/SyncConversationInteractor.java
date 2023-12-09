package services.conversation.sync_conversation_view;

import entities.Contact;
import entities.Message;
import entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SyncConversationInteractor implements SyncConversationInputBoundary {
    final SyncConversationDataAccessInterface conversationSyncDataAccessObject;
    final SyncConversationOutputBoundary conversationSyncPresenter;

    public SyncConversationInteractor(SyncConversationDataAccessInterface conversationSyncDataAccessObject,
                                      SyncConversationOutputBoundary conversationSyncPresenter) {
        this.conversationSyncDataAccessObject = conversationSyncDataAccessObject;
        this.conversationSyncPresenter = conversationSyncPresenter;
    }

    @Override
    public void execute(SyncConversationInputData syncConversationInputData) {
        conversationSyncDataAccessObject.syncUser();
        User currentUser = conversationSyncDataAccessObject.getUser();
        Contact contact = currentUser.getContact(syncConversationInputData.getContactName());
        List<Message> messages = contact.getMessages();

        Map<LocalDateTime, List<String>> timestampToMessage = new TreeMap<>();

        for (Message message : messages) {
            ArrayList<String> messageData = new ArrayList<>();
            messageData.add(message.getSender());
            messageData.add(message.getContent());
            timestampToMessage.put(message.getMessageTime(), messageData);
        }

        SyncConversationOutputData outputData = new SyncConversationOutputData(timestampToMessage);
        conversationSyncPresenter.prepareSuccessView(outputData);
    }
}
