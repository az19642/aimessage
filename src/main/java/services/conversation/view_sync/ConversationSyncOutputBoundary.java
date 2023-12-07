package services.conversation.view_sync;

public interface ConversationSyncOutputBoundary {

    void prepareSuccessView(ConversationSyncOutputData conversationSyncOutputData);

    void prepareFailView(String error);
}
