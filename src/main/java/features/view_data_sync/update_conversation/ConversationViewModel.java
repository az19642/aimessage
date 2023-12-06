package features.view_data_sync.update_conversation;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents the view model for the conversation view.
 * Contains the state of the conversation view.
 */
public class ConversationViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ConversationState state = new ConversationState();

    public ConversationViewModel() {
        super("conversation");
    }

    /**
     * Notifies listeners of a property change in the ViewModel.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * @param listener The listener to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * @return The state of the conversation view.
     */
    public ConversationState getState() {
        return this.state;
    }

    /**
     * @param state The new state to be set.
     */
    public void setState(ConversationState state) {
        this.state = state;
    }
}
