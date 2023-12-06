package features.auth.login;

/**
 * Represents the state of a user during the login process.
 */
public class LoginState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String loginError = null;

    public LoginState(LoginState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoginState() {
    }

    /**
     * Gets the username entered for login.
     *
     * @return The username entered for login.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username entered for login.
     *
     * @param username The new username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the error message related to the username during login.
     *
     * @return The error message related to the username during login.
     */
    public String getUsernameError() {
        return usernameError;
    }

    /**
     * Sets the error message related to the username during login.
     *
     * @param usernameError The new error message to be set.
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * Gets the password entered for login.
     *
     * @return The password entered for login.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password entered for login.
     *
     * @param password The new password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the error message related to the password during login.
     *
     * @return The error message related to the password during login.
     */
    public String getPasswordError() {
        return passwordError;
    }

    /**
     * Sets the error message related to the password during login.
     *
     * @param passwordError The new error message to be set.
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    /**
     * Sets the error message related to the login process.
     * Compared to the username and password errors, this error is more generic.
     *
     * @param loginError The new error message to be set.
     */
    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }

    /**
     * Gets the error message related to the login process.
     * Compared to the username and password errors, this error is more generic.
     *
     * @return The error message related to the login process.
     */
    public String getLoginError() {
        return loginError;
    }
}