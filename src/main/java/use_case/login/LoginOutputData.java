package use_case.login;

/**
 * Represents the output data for the login use case.
 */
public class LoginOutputData {

    private final String username;

    public LoginOutputData(String username) {
        this.username = username;
    }

    /**
     * Gets the username of the logged-in user.
     *
     * @return The username of the logged-in user.
     */
    public String getUsername() {
        return username;
    }

}