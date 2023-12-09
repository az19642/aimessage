package services.signup.interface_adapters;

import interface_adapters.ViewManagerModel;
import services.login.LoginState;
import services.login.interface_adapters.LoginViewModel;
import services.signup.SignupOutputBoundary;
import services.signup.SignupOutputData;
import services.signup.SignupState;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Presenter responsible for handling the output of the signup use case and updating the corresponding ViewModel.
 */
public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a new SignupPresenter with the specified dependencies.
     *
     * @param viewManagerModel The ViewManagerModel used for managing views.
     * @param signupViewModel  The SignupViewModel representing the state of the signup screen.
     * @param loginViewModel   The LoginViewModel representing the state of the login screen.
     */
    public SignupPresenter(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * Prepares the view for a successful signup by updating the login state and switching to the login view.
     *
     * @param response The output data containing information about the successful signup.
     */
    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // On success, switch to the login view.
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        // Create a fresh login state
        LoginState loginState = new LoginState();

        loginState.setUsername(response.getUsername());

        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view for a failed signup by updating the signup state with an error message.
     *
     * @param error The error message indicating the reason for the signup failure.
     */
    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }
}