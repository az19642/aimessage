package use_case.login;

/**
 * Interface representing the output boundary for the login use case.
 */
public interface LoginOutputBoundary {

    /**
     * Prepares the view for a successful login by providing the necessary data.
     *
     * @param user The output data containing information about the logged-in user.
     */
    void prepareSuccessView(LoginOutputData user);

    /**
     * Prepares the view for a failed login by providing an error message.
     *
     * @param error The error message indicating the reason for the login failure.
     */
    void prepareFailView(String error);
}