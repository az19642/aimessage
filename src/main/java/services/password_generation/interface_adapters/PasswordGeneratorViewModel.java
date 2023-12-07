package services.password_generation.interface_adapters;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents the ViewModel for the password generator screen.
 * Manages the state and property change listeners for password generation.
 */
public class PasswordGeneratorViewModel extends ViewModel {
    private PasswordGeneratorState state = new PasswordGeneratorState();

    /**
     * Constructs a new PasswordGeneratorViewModel with a default message.
     */
    public PasswordGeneratorViewModel() {
        super("Generate a secure password.");
    }

    /**
     * Sets the state of the password generator.
     *
     * @param state The new state to set.
     */
    public void setState(PasswordGeneratorState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Fires a property change event indicating a change in the ViewModel's state.
     */
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
    public PasswordGeneratorState getState() {
        return state;
    }
}
