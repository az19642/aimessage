package interface_adapter.text_to_speech;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TextToSpeechViewModel extends ViewModel {
    private TextToSpeechState state = new TextToSpeechState();

    public TextToSpeechViewModel() {
        super("Create speech from text.");
    }

    public void setState(TextToSpeechState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}

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
     * Gets the current state of the password generator.
     *
     * @return The current state of the password generator.
     */
    public TextToSpeechState getState() {
        return state;
    }
}
