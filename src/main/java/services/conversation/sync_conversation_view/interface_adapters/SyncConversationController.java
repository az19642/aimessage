package services.conversation.sync_conversation_view.interface_adapters;

import services.conversation.sync_conversation_view.SyncConversationInputBoundary;
import services.conversation.sync_conversation_view.SyncConversationInputData;

public class SyncConversationController {
    final SyncConversationInputBoundary conversationSyncInteractor;

    public SyncConversationController(SyncConversationInputBoundary conversationSyncInteractor) {
        this.conversationSyncInteractor = conversationSyncInteractor;
    }

    public void execute(String contactName) {
        SyncConversationInputData syncConversationInputData = new SyncConversationInputData(contactName);
        conversationSyncInteractor.execute(syncConversationInputData);
    }
}
