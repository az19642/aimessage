package interface_adapter.translator;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents the view model for handling translation-related data in the user interface.
 */
public class TranslatorViewModel extends ViewModel {
    private TranslatorState state = new TranslatorState();

    /**
     * Constructs a new TranslatorViewModel with a default view name.
     */
    public TranslatorViewModel() {
        super("Create translation from text.");
    }

    /**
     * Sets the state of the translator view model.
     *
     * @param state The new state to set.
     */
    public void setState(TranslatorState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies registered listeners about changes in the translator view model state.
     */
    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}

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
    public TranslatorState getState() {
        return state;
    }
}
