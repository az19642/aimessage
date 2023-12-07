package services.state_tests;

import data_access.MongoDataAccessObject;
import entities.CommonUserFactory;
import org.junit.jupiter.api.Test;
import services.signup.SignupState;

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
