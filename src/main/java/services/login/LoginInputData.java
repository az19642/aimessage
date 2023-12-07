package services.login;

/**
 * Represents the input data for the login use case.
 */
public class LoginInputData {

    final private String username;
    final private String password;

    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username for login.
     *
     * @return The username for login.
     */
    String getUsername() {
        return username;
    }

    /**
     * Gets the password for login.
     *
     * @return The password for login.
     */
    String getPassword() {
        return password;
    }

}