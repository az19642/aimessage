package services.conversation.view_sync.interface_adapters;

import services.conversation.interface_adapters.ConversationState;
import services.conversation.interface_adapters.ConversationViewModel;
import services.conversation.view_sync.ConversationSyncOutputBoundary;
import services.conversation.view_sync.ConversationSyncOutputData;

public class ConversationSyncPresenter implements ConversationSyncOutputBoundary {
    private final ConversationViewModel conversationViewModel;

    public ConversationSyncPresenter(ConversationViewModel conversationViewModel) {
        this.conversationViewModel = conversationViewModel;
    }

    @Override
    public void prepareSuccessView(ConversationSyncOutputData outputData) {
        ConversationState state = conversationViewModel.getState();
        state.getTimestampToMessage();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
