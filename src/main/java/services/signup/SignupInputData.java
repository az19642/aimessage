package services.signup;

/**
 * Represents the input data for the signup use case.
 */
public class SignupInputData {

    final private String username;
    final private String password;
    final private String repeatPassword;
    private String preferredLanguage;

    public SignupInputData(String username, String password, String repeatPassword, String preferredLanguage) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.preferredLanguage = preferredLanguage;
    }

    /**
     * Gets the username chosen during the signup process.
     *
     * @return The username.
     */
    String getUsername() {
        return username;
    }

    /**
     * Gets the password chosen during the signup process.
     *
     * @return The password.
     */
    String getPassword() {
        return password;
    }

    /**
     * Gets the repeated password entered during the signup process for confirmation.
     *
     * @return The repeated password.
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Gets the preferred language selected during the signup process.
     *
     * @return The preferred language.
     */
    String getPreferredLanguage() {
        return preferredLanguage;
    }
}