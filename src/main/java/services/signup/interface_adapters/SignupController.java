package services.signup.interface_adapters;

import services.signup.SignupInputBoundary;
import services.signup.SignupInputData;

/**
 * Controller responsible for handling user signup-related interactions and invoking the corresponding use case.
 */
public class SignupController {

    final SignupInputBoundary userSignupUseCaseInteractor;

    /**
     * Constructs a new SignupController with the specified SignupInputBoundary.
     *
     * @param userSignupUseCaseInteractor The SignupInputBoundary to be used by the controller.
     */
    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the user signup action with the given username, passwords, and preferred language.
     * Converts the input parameters into a SignupInputData object and assigns the execution to the use case.
     *
     * @param username The username provided for signup.
     * @param password1 The first password provided for signup.
     * @param password2 The repeated password provided for signup.
     * @param preferredLanguage The preferred language selected during signup.
     */
    public void execute(String username, String password1, String password2, String preferredLanguage) {
        SignupInputData signupInputData = new SignupInputData(
                username, password1, password2, preferredLanguage);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}