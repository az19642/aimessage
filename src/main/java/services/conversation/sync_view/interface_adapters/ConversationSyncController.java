package services.conversation.sync_view.interface_adapters;

import services.conversation.sync_view.ConversationSyncInputBoundary;
import services.conversation.sync_view.ConversationSyncInputData;

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
