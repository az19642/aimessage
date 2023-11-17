package use_case.signup;

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

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    String getPreferredLanguage() {
        return preferredLanguage;
    }
}
