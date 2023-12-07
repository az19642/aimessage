package services.conversation.sync_conversation_view.interface_adapters;

import services.conversation.interface_adapters.ConversationState;
import services.conversation.interface_adapters.ConversationViewModel;
import services.conversation.sync_conversation_view.ConversationSyncOutputBoundary;
import services.conversation.sync_conversation_view.ConversationSyncOutputData;

public class ConversationSyncPresenter implements ConversationSyncOutputBoundary {
    private final ConversationViewModel conversationViewModel;

    public ConversationSyncPresenter(ConversationViewModel conversationViewModel) {
        this.conversationViewModel = conversationViewModel;
    }

    @Override
    public void prepareSuccessView(ConversationSyncOutputData conversationSyncOutputData) {
        ConversationState state = conversationViewModel.getState();
        state.setTimestampToMessage(conversationSyncOutputData.getTimestampToMessage());
        conversationViewModel.setState(state);
        conversationViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
