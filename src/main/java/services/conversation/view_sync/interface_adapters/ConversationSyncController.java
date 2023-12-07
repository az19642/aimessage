package services.conversation.view_sync.interface_adapters;

import services.conversation.view_sync.ConversationSyncInputBoundary;
import services.conversation.view_sync.ConversationSyncInputData;

public class ConversationSyncController {
    final ConversationSyncInputBoundary interactor;

    public ConversationSyncController(ConversationSyncInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String contactName) {
        ConversationSyncInputData inputData = new ConversationSyncInputData(contactName);
        interactor.execute(inputData);
    }
}
