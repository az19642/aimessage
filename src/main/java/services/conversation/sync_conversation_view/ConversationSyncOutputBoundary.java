package services.conversation.sync_conversation_view;

public interface ConversationSyncOutputBoundary {

    void prepareSuccessView(ConversationSyncOutputData conversationSyncOutputData);

    void prepareFailView(String error);
}
