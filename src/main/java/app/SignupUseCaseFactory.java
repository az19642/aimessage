package app;

import entities.CommonUserFactory;
import entities.UserFactory;
import interface_adapter.ViewManagerModel;
import services.login.interface_adapters.LoginViewModel;
import services.password_generation.interface_adapters.PasswordGeneratorController;
import services.password_generation.interface_adapters.PasswordGeneratorPresenter;
import services.password_generation.interface_adapters.PasswordGeneratorViewModel;
import services.signup.interface_adapters.SignupController;
import services.signup.interface_adapters.SignupPresenter;
import services.signup.interface_adapters.SignupViewModel;
import services.password_generation.PasswordGeneratorInputBoundary;
import services.password_generation.PasswordGeneratorInteractor;
import services.password_generation.PasswordGeneratorOutputBoundary;
import services.password_generation.PasswordGeneratorUserDataAccessInterface;
import services.signup.SignupInputBoundary;
import services.signup.SignupInteractor;
import services.signup.SignupOutputBoundary;
import services.signup.SignupUserDataAccessInterface;
import views.SignupView;

import javax.swing.*;
import java.io.IOException;

/**
 * A factory class responsible for creating and initializing components related to the Signup use case.
 * It provides methods for creating and configuring the SignupView and its associated controllers and use case components.
 */
public class SignupUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private SignupUseCaseFactory() {
    }

    /**
     * Creates and configures a SignupView along with its associated controllers and use case components.
     *
     * @param viewManagerModel                  The view manager model for managing application views.
     * @param loginViewModel                    The view model for the login interface.
     * @param signupViewModel                   The view model for the signup interface.
     * @param userDataAccessObject              The data access object for user-related operations.
     * @param passwordGeneratorViewModel        The view model for the password generator interface.
     * @param passwordGeneratorDataAccessObject The data access object for password generation operations.
     * @return A configured SignupView or null if there's an error opening the user data file.
     */
    public static SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel,
            SignupUserDataAccessInterface userDataAccessObject, PasswordGeneratorViewModel passwordGeneratorViewModel,
            PasswordGeneratorUserDataAccessInterface passwordGeneratorDataAccessObject) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            PasswordGeneratorController passwordGeneratorController = createUserPasswordGeneratorUseCase(viewManagerModel, passwordGeneratorViewModel, passwordGeneratorDataAccessObject);
            return new SignupView(signupController, signupViewModel, passwordGeneratorViewModel, loginViewModel, viewManagerModel, passwordGeneratorController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(
            ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel,
            SignupUserDataAccessInterface userDataAccessObject) throws IOException {

        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }

    private static PasswordGeneratorController createUserPasswordGeneratorUseCase(
            ViewManagerModel viewManagerModel, PasswordGeneratorViewModel passwordGeneratorViewModel,
            PasswordGeneratorUserDataAccessInterface passwordGeneratorDataAccessObject) throws IOException {

        PasswordGeneratorOutputBoundary passwordGeneratorOutputBoundary = new PasswordGeneratorPresenter(viewManagerModel, passwordGeneratorViewModel);

        PasswordGeneratorInputBoundary passwordGeneratorInteractor = new PasswordGeneratorInteractor(passwordGeneratorDataAccessObject, passwordGeneratorOutputBoundary);

        return new PasswordGeneratorController(passwordGeneratorInteractor);
    }
}