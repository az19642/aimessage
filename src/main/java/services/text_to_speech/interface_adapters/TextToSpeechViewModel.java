package services.text_to_speech.interface_adapters;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel class for text-to-speech functionality.
 * Extends the base ViewModel class and provides functionality for managing the state of text-to-speech operations.
 */
public class TextToSpeechViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private TextToSpeechState state = new TextToSpeechState();

    /**
     * Constructs a new TextToSpeechViewModel with a default view name.
     */
    public TextToSpeechViewModel() {
        super("Create speech from text.");
    }

    /**
     * Fires a property change event to notify listeners about changes in the ViewModel state.
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
     * Gets the current state of the text-to-speech ViewModel.
     *
     * @return The current state of the text-to-speech ViewModel.
     */
    public TextToSpeechState getState() {
        return state;
    }

    /**
     * Sets the state of the text-to-speech ViewModel.
     *
     * @param state The new state to set.
     */
    public void setState(TextToSpeechState state) {
        this.state = state;
    }
}
