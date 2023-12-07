package services.signup;

/**
 * Interface representing the input boundary for the signup use case.
 */
public interface SignupInputBoundary {
    void execute(SignupInputData signupInputData);
}