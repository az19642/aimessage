package interface_adapter.logged_in;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel class representing the state and behavior of the "Logged In" view.
 */
public class LoggedInViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Contacts";
    public static final String HOME_BUTTON_LABEL = "Go Home";
    public static final String ADD_BUTTON_LABEL = "Add Contact";
    public static final String DELETE_BUTTON_LABEL = "Delete Contact";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private LoggedInState state = new LoggedInState();
    private String loggedInUser;

    public LoggedInViewModel() {
        super("logged in");
    }

    /**
     * Notifies listeners of a property change in the ViewModel.
     * This method is called by the Login Presenter to alert the View.
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
     * Gets the current state of the logged-in user.
     *
     * @return The state of the logged-in user.
     */
    public LoggedInState getState() {
        return state;
    }

    /**
     * Sets the state of the logged-in user.
     *
     * @param state The new state to be set.
     */
    public void setState(LoggedInState state) {
        this.state = state;
    }

    /**
     * Gets the username of the logged-in user.
     *
     * @return The username of the logged-in user.
     */
    public String getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Sets the logged-in user.
     *
     * @param loggedInUser The new user to be set.
     */
    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}