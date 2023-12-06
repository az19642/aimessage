package features.auth.login.interface_adapters;

import features.auth.login.LoginState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel class representing the state and behavior of the "Log In" view.
 */
public class LoginViewModel extends ViewModel {

    public static final String LOGIN_BUTTON_LABEL = "Log in";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String USERNAME_LABEL = "Username";
    public static final String PASSWORD_LABEL = "Password";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private LoginState state = new LoginState();

    public LoginViewModel() {
        super("log in");
    }

    /**
     * Notifies listeners of a property change in the ViewModel.
     * This method is called by the Signup Presenter to alert the View.
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
     * Gets the current state of the login view.
     *
     * @return The state of the login view.
     */
    public LoginState getState() {
        return state;
    }

    /**
     * Sets the state of the login view.
     *
     * @param state The new state to be set.
     */
    public void setState(LoginState state) {
        this.state = state;
    }
}