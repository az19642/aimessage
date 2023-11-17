package interface_adapter.logged_in;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedInViewModel extends ViewModel {
    public static final String LOGOUT_BUTTON_LABEL = "Log out";
    public final String TITLE_LABEL = "Logged In View";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private LoggedInState state = new LoggedInState();
    private String loggedInUser;

    public LoggedInViewModel() {
        super("logged in");
    }

    // This is what the Login Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoggedInState getState() {
        return state;
    }

    public void setState(LoggedInState state) {
        this.state = state;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
