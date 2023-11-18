package interface_adapter.signup;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel representing the state of the sign-up view.
 */
public class SignupViewModel extends ViewModel {

    public static final String CLEAR_BUTTON_LABEL = "Clear";
    public static final String TITLE_LABEL = "Sign Up View";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";

    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private SignupState state = new SignupState();

    public SignupViewModel() {
        super("sign up");
    }

    /**
     * Notifies listeners that a property in the ViewModel has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the ViewModel.
     *
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state of the sign-up view.
     *
     * @return The current state of the sign-up view.
     */
    public SignupState getState() {
        return state;
    }

    /**
     * Sets the state of the sign-up view.
     *
     * @param state The new state to be set.
     */
    public void setState(SignupState state) {
        this.state = state;
    }
}