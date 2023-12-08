package services.conversation.sync_conversation_view.interface_adapters;

import services.conversation.interface_adapters.ConversationState;
import services.conversation.interface_adapters.ConversationViewModel;
import services.conversation.sync_conversation_view.SyncConversationOutputBoundary;
import services.conversation.sync_conversation_view.SyncConversationOutputData;

public class SyncConversationPresenter implements SyncConversationOutputBoundary {
    private final ConversationViewModel conversationViewModel;

    public SyncConversationPresenter(ConversationViewModel conversationViewModel) {
        this.conversationViewModel = conversationViewModel;
    }

    @Override
    public void prepareSuccessView(SyncConversationOutputData syncConversationOutputData) {
        ConversationState state = conversationViewModel.getState();
        state.setTimestampToMessage(syncConversationOutputData.getTimestampToMessage());
        conversationViewModel.setState(state);
        conversationViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
