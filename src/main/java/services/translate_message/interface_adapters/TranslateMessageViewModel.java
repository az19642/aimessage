package services.translate_message.interface_adapters;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents the view model for handling translation-related data in the user interface.
 */
public class TranslateMessageViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private TranslateMessageState state = new TranslateMessageState();

    /**
     * Constructs a new TranslatorViewModel with a default view name.
     */
    public TranslateMessageViewModel() {
        super("Create translation from text.");
    }

    /**
     * Notifies registered listeners about changes in the translator view model state.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the translator view model.
     *
     * @param listener The listener to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state of the translator view model.
     *
     * @return The current state of the translator view model.
     */
    public TranslateMessageState getState() {
        return state;
    }

    /**
     * Sets the state of the translator view model.
     *
     * @param state The new state to set.
     */
    public void setState(TranslateMessageState state) {
        this.state = state;
    }
}
