package app;

import interface_adapters.ViewManagerModel;
import services.logged_in.interface_adapters.LoggedInViewModel;
import services.login.LoginDataAccessInterface;
import services.login.LoginInputBoundary;
import services.login.LoginInteractor;
import services.login.LoginOutputBoundary;
import services.login.interface_adapters.LoginController;
import services.login.interface_adapters.LoginPresenter;
import services.login.interface_adapters.LoginViewModel;
import services.signup.interface_adapters.SignupViewModel;
import views.LoginView;

/**
 * A factory for creating the login view.
 */
public class LoginViewFactory {

    private LoginViewFactory() {
    }

    /**
     * Creates a new instance of the login view.
     *
     * @param viewManagerModel         The view manager model.
     * @param loginViewModel           The login view model.
     * @param signupViewModel          The signup view model.
     * @param loggedInViewModel        The logged in view model.
     * @param loginDataAccessInterface The data access object for logging in.
     * @return The login view.
     */
    public static LoginView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
                                   SignupViewModel signupViewModel, LoggedInViewModel loggedInViewModel,
                                   LoginDataAccessInterface loginDataAccessInterface) {

        LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel,
                loginDataAccessInterface);

        return LoginView.getInstance(loginViewModel, signupViewModel, loginController, viewManagerModel);

    }

    private static LoginController createLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
                                                      LoggedInViewModel loggedInViewModel,
                                                      LoginDataAccessInterface loginDataAccessInterface) {

        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel,
                loginViewModel);

        LoginInputBoundary loginInteractor = new LoginInteractor(loginDataAccessInterface, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}