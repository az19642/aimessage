package services.conversation.sync_conversation_view;

public interface SyncConversationOutputBoundary {

    void prepareSuccessView(SyncConversationOutputData syncConversationOutputData);

    void prepareFailView(String error);
}
