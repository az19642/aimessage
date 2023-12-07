package services.conversation.sync_view;

public interface ConversationSyncOutputBoundary {

    void prepareSuccessView(ConversationSyncOutputData conversationSyncOutputData);

    void prepareFailView(String error);
}
