package services.signup;

/**
 * Represents the state of a user during the signup process.
 */
public class SignupState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;
    private String preferredLanguage = "";
    private String preferredLanguageError = null;

    /**
     * Constructs a new SignupState by copying the values from the provided SignupState.
     *
     * @param copy The SignupState to copy values from.
     */
    public SignupState(SignupState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
        repeatPassword = copy.repeatPassword;
        repeatPasswordError = copy.repeatPasswordError;
        preferredLanguage = copy.preferredLanguage;
        preferredLanguageError = copy.preferredLanguageError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SignupState() {
    }

    /**
     * Gets the username entered during signup.
     *
     * @return The username entered during signup.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username entered during signup.
     *
     * @param username The new username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the error message related to the username during signup.
     *
     * @return The error message related to the username during signup.
     */

    public String getUsernameError() {
        return usernameError;
    }

    /**
     * Sets the error message related to the username during signup.
     *
     * @param usernameError The new error message to be set.
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * Gets the password entered during signup.
     *
     * @return The password entered during signup.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password entered during signup.
     *
     * @param password The new password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the error message related to the password during signup.
     *
     * @return The error message related to the password during signup.
     */
    public String getPasswordError() {
        return passwordError;
    }

    /**
     * Sets the error message related to the password during signup.
     *
     * @param passwordError The new error message to be set.
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    /**
     * Gets the repeated password entered during signup.
     *
     * @return The repeated password entered during signup.
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Sets the repeated password entered during signup.
     *
     * @param repeatPassword The new repeated password to be set.
     */
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    /**
     * Gets the error message related to the repeated password during signup.
     *
     * @return The error message related to the repeated password during signup.
     */
    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    /**
     * Sets the error message related to the repeated password during signup.
     *
     * @param repeatPasswordError The new error message to be set.
     */
    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    /**
     * Gets the preferred language entered during signup.
     *
     * @return The preferred language entered during signup.
     */
    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    /**
     * Sets the preferred language entered during signup.
     *
     * @param preferredLanguage The new preferred language to be set.
     */
    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    /**
     * Gets the error message related to the preferred language during signup.
     *
     * @return The error message related to the preferred language during signup.
     */
    public String getPreferredLanguageError() {
        return preferredLanguageError;
    }

    /**
     * Sets the error message related to the preferred language during signup.
     *
     * @param preferredLanguageError The new error message to be set.
     */
    public void setPreferredLanguageError(String preferredLanguageError) {
        this.preferredLanguageError = preferredLanguageError;
    }

    /**
     * Provides a string representation of the SignupState for debugging purposes.
     *
     * @return A string representation of the SignupState.
     */
    @Override
    public String toString() {
        return "SignupState{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", repeatPassword='" + repeatPassword + '\'' + '}';
    }
}