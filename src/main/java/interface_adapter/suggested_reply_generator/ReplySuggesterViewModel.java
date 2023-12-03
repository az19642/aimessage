package interface_adapter.suggested_reply_generator;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents the ViewModel for the suggested reply generator screen.
 * Manages the state and property change listeners for suggested reply generation.
 */
public class ReplySuggesterViewModel extends ViewModel {
    private ReplySuggesterState state = new ReplySuggesterState();

    /**
     * Constructs a new ReplySuggesterViewModel with a default message.
     */
    public ReplySuggesterViewModel(){
        super("Suggest a reply under 200 characters.");
    }

    /**
     * Sets the state of the suggested reply generator.
     *
     * @param state The new state to set.
     */
    public void setState(ReplySuggesterState state) { this.state = state; }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
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
    public ReplySuggesterState getState() { return state; }
}
