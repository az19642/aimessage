package app;

import entities.CommonUserFactory;
import entities.UserFactory;
import interface_adapter.ViewManagerModel;
import features.logged_in.LoggedInViewModel;
import features.auth.login.interface_adapters.LoginController;
import features.auth.login.interface_adapters.LoginPresenter;
import features.auth.login.interface_adapters.LoginViewModel;
import features.auth.signup.interface_adapters.SignupViewModel;
import features.auth.login.LoginInputBoundary;
import features.auth.login.LoginInteractor;
import features.auth.login.LoginOutputBoundary;
import features.auth.login.LoginUserDataAccessInterface;
import views.LoginView;

/**
 * A factory class responsible for creating and initializing components related to the login use case.
 */
public class LoginUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private LoginUseCaseFactory() {
    }

    /**
     * Creates and configures a LoginView along with its associated controllers and use case components.
     *
     * @param viewManagerModel     The view manager model for managing application views.
     * @param loginViewModel       The view model for the login interface.
     * @param signupViewModel      The view model for the signup interface.
     * @param loggedInViewModel    The view model for the logged in interface.
     * @param userDataAccessObject The data access object for user-related operations.
     * @return A configured LoginView
     */
    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            SignupViewModel signupViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject) {

        LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel,
                userDataAccessObject);
        return new LoginView(loginViewModel, signupViewModel, loginController, viewManagerModel);

    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject) {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel,
                loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}