package interface_adapter.suggested_reply_generator;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ReplySuggesterViewModel extends ViewModel {
    private ReplySuggesterState state = new ReplySuggesterState();

    public ReplySuggesterViewModel(){
        super("Suggest a reply under 200 characters.");
    }

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

    public ReplySuggesterState getState() { return state; }
}
