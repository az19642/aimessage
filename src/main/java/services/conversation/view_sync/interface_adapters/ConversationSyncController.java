package services.conversation.view_sync.interface_adapters;

import services.conversation.view_sync.ConversationSyncInputBoundary;
import services.conversation.view_sync.ConversationSyncInputData;

public class ConversationSyncController {
    final ConversationSyncInputBoundary conversationSyncInteractor;

    public ConversationSyncController(ConversationSyncInputBoundary conversationSyncInteractor) {
        this.conversationSyncInteractor = conversationSyncInteractor;
    }

    public void execute(String contactName) {
        ConversationSyncInputData conversationSyncInputData = new ConversationSyncInputData(contactName);
        conversationSyncInteractor.execute(conversationSyncInputData);
    }
}
