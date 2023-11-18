package interface_adapter.logged_in;

/**
 * Represents the state of a user who is currently logged in.
 */
public class LoggedInState {
    private String username = "";

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {
    }

    /**
     * Gets the username of the logged-in user.
     *
     * @return The username of the logged-in user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the logged-in user.
     *
     * @param username The new username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}