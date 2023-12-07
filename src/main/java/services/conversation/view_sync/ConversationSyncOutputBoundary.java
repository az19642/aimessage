package services.conversation.view_sync;

public interface ConversationSyncOutputBoundary {

    void prepareSuccessView(ConversationSyncOutputData outputData);

    void prepareFailView(String error);
}
