package interface_adapter.signup;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel representing the state of the sign-up view.
 */
public class SignupViewModel extends ViewModel {
    public static final String TITLE_LABEL = "AiMessage";
    public static final String GROUP_LABEL = "Group 178";
    public static final String USERNAME_LABEL = "Username";
    public static final String PASSWORD_LABEL = "Password";
    public static final String REPEAT_PASSWORD_LABEL = "Confirm Password";

    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String GENERATE_PASSWORD_LABEL = "Generate Password";
    public static final String PREFERRED_LANGUAGE_LABEL = "Preferred Language";

    public static final String GO_TO_LOGIN_BUTTON_LABEL = "Go to login";
    public static final String HAVE_ACCOUNT_LABEL = "Already a user?";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private SignupState state = new SignupState();

    /**
     * Constructs a new SignupViewModel with a default view name.
     */
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