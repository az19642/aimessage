package use_case.signup;

/**
 * Interface representing the input boundary for the signup use case.
 */
public interface SignupInputBoundary {

    /**
     * Executes the signup use case with the provided input data.
     *
     * @param signupInputData The input data for the signup use case.
     */
    void execute(SignupInputData signupInputData);
}