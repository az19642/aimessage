package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.password_generator.PasswordGeneratorController;
import interface_adapter.password_generator.PasswordGeneratorPresenter;
import interface_adapter.password_generator.PasswordGeneratorViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.password_generator.PasswordGeneratorInputBoundary;
import use_case.password_generator.PasswordGeneratorInteractor;
import use_case.password_generator.PasswordGeneratorOutputBoundary;
import use_case.password_generator.PasswordGeneratorUserDataAccessInterface;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import view.SignupView;

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