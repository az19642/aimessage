package app;

import entities.CommonUserFactory;
import entities.UserFactory;
import interface_adapter.ViewManagerModel;
import services.logged_in.LoggedInViewModel;
import services.login.interface_adapters.LoginController;
import services.login.interface_adapters.LoginPresenter;
import services.login.interface_adapters.LoginViewModel;
import services.signup.interface_adapters.SignupViewModel;
import services.login.LoginInputBoundary;
import services.login.LoginInteractor;
import services.login.LoginOutputBoundary;
import services.login.LoginUserDataAccessInterface;
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