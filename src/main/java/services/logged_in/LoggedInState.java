package services.logged_in;

import java.util.Map;

/**
 * Represents the state of a user who is currently logged in.
 */
public class LoggedInState {
    private String username = "";
    private Map<String, String> contactToLastMessage;

    private String mutatingContactsStatus = "";

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
        return this.username;
    }

    /**
     * Sets the username of the logged-in user.
     *
     * @param username The new username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the map of contacts to their last message.
     *
     * @return The map of contacts to their last message.
     */
    public Map<String, String> getContactToLastMessage() {
        return this.contactToLastMessage;
    }

    /**
     * Sets the map of contacts to their last message.
     *
     * @param contactToLastMessage The new map of contacts to their last message.
     */
    public void setContactToLastMessage(Map<String, String> contactToLastMessage) {
        this.contactToLastMessage = contactToLastMessage;
    }

    /**
     * Gets the error message for a failed mutation of contacts.
     *
     * @return The error message for a failed mutation of contacts.
     */
    public String getMutatingContactsStatus() {
        return this.mutatingContactsStatus;
    }

    /**
     * Sets the error message for a failed mutation of contacts.
     *
     * @param mutatingContactsStatus The new error message for a failed mutation of contacts.
     */
    public void setMutatingContactsStatus(String mutatingContactsStatus) {
        this.mutatingContactsStatus = mutatingContactsStatus;
    }
}