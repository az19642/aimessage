package services.conversation.sync_conversation_view.interface_adapters;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;

public class SyncConversationViewModel extends ViewModel {

    public SyncConversationViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
