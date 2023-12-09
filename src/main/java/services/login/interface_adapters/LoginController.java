package services.login.interface_adapters;

import services.login.LoginInputBoundary;
import services.login.LoginInputData;

/**
 * Controller responsible for handling login-related interactions and invoking the corresponding use case.
 */
public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the login action with the given username and password.
     * Converts the input parameters into a LoginInputData object and assigns the execution to the use case.
     *
     * @param username The username provided for login.
     * @param password The password provided for login.
     */
    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
}