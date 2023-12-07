package services.login.interface_adapters;

import services.logged_in.LoggedInViewModel;
import services.login.LoginOutputBoundary;
import services.login.LoginOutputData;
import services.login.LoginState;
import interface_adapter.ViewManagerModel;
import services.logged_in.LoggedInState;

/**
 * Presenter responsible for handling the output of the login use case and updating the corresponding ViewModel.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * Prepares the view for a successful login by updating the logged-in state and switching to the logged-in view.
     *
     * @param response The output data containing information about the successful login.
     */
    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view for a failed login by updating the login state with an error message.
     *
     * @param error The error message indicating the reason for the login failure.
     */
    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}