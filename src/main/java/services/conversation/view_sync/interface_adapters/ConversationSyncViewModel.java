package services.conversation.view_sync.interface_adapters;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class ConversationSyncViewModel extends ViewModel {

    public ConversationSyncViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
