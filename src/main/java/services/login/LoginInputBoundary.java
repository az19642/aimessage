package services.login;

/**
 * Interface representing the input boundary for the login use case.
 */
public interface LoginInputBoundary {

    /**
     * Executes the login use case with the provided login input data.
     *
     * @param loginInputData The input data for the login use case.
     */
    void execute(LoginInputData loginInputData);
}