package services.suggest_reply.interface_adapters;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents the ViewModel for the suggested reply generator screen.
 * Manages the state and property change listeners for suggested reply generation.
 */
public class SuggestReplyViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private SuggestReplyState state = new SuggestReplyState();

    /**
     * Constructs a new ReplySuggesterViewModel with a default message.
     */
    public SuggestReplyViewModel() {
        super("Suggest a reply under 200 characters.");
    }

    /**
     * Notifies listeners that a property in the ViewModel has changed.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the ViewModel.
     *
     * @param listener The listener to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state of the suggested reply generator.
     *
     * @return The current state of the suggested reply generator.
     */
    public SuggestReplyState getState() {
        return state;
    }

    /**
     * Sets the state of the suggested reply generator.
     *
     * @param state The new state to set.
     */
    public void setState(SuggestReplyState state) {
        this.state = state;
    }
}
