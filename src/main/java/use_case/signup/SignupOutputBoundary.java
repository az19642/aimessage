package use_case.signup;

/**
 * Defines the output boundary for the signup use case, responsible for preparing views based on the outcome.
 */
public interface SignupOutputBoundary {

    /**
     * Prepares the view for a successful signup, providing the necessary output data.
     *
     * @param user The output data representing the result of a successful signup.
     */
    void prepareSuccessView(SignupOutputData user);

    /**
     * Prepares the view for a failed signup, providing an error message.
     *
     * @param error The error message indicating the reason for the failed signup.
     */
    void prepareFailView(String error);
}