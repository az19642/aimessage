package services.signup;

import org.junit.jupiter.api.Test;

public class SignupStateTest {

    @Test
    void execute() {

        SignupState signupState = new SignupState(new SignupState());

        signupState.getUsernameError();
        signupState.setUsernameError("");
        signupState.getPasswordError();
        signupState.setPasswordError("");
        signupState.getRepeatPasswordError();
        signupState.setRepeatPasswordError("");
        signupState.getPreferredLanguage();
        signupState.setPreferredLanguageError("");
        signupState.toString();


    }

}
