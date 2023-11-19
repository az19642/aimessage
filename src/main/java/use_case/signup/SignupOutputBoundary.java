package use_case.signup;

/**
 * Defines the output boundary for the signup use case, responsible for preparing views based on the outcome.
 */
public interface SignupOutputBoundary {
    void prepareSuccessView(SignupOutputData user);

    void prepareFailView(String error);
}