package app;

import entities.CommonUserFactory;
import entities.UserFactory;
import interface_adapters.ViewManagerModel;
import services.generate_password.GeneratePasswordDataAccessInterface;
import services.generate_password.GeneratePasswordInputBoundary;
import services.generate_password.GeneratePasswordInteractor;
import services.generate_password.GeneratePasswordOutputBoundary;
import services.generate_password.interface_adapters.GenerateGeneratePasswordPresenter;
import services.generate_password.interface_adapters.GeneratePasswordController;
import services.generate_password.interface_adapters.GeneratePasswordViewModel;
import services.login.interface_adapters.LoginViewModel;
import services.signup.SignupDataAccessInterface;
import services.signup.SignupInputBoundary;
import services.signup.SignupInteractor;
import services.signup.SignupOutputBoundary;
import services.signup.interface_adapters.SignupController;
import services.signup.interface_adapters.SignupPresenter;
import services.signup.interface_adapters.SignupViewModel;
import views.SignupView;

import javax.swing.*;
import java.io.IOException;

/**
 * A factory for creating the signup view.
 */
public class SignupViewFactory {

    private SignupViewFactory() {
    }


    /**
     * Creates the signup view.
     *
     * @param viewManagerModel                    the view manager model
     * @param loginViewModel                      the login view model
     * @param signupViewModel                     the signup view model
     * @param generatePasswordViewModel           the generate password view model
     * @param signupDataAccessInterface           the signup data access interface
     * @param generatePasswordDataAccessInterface the generate password data access interface
     * @return the signup view
     */
    public static SignupView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
                                    SignupViewModel signupViewModel,
                                    GeneratePasswordViewModel generatePasswordViewModel,
                                    SignupDataAccessInterface signupDataAccessInterface,
                                    GeneratePasswordDataAccessInterface generatePasswordDataAccessInterface) {

        try {
            SignupController signupController = createSignupController(viewManagerModel, signupViewModel,
                    loginViewModel, signupDataAccessInterface);
            GeneratePasswordController generatePasswordController = createGeneratePasswordController(viewManagerModel,
                    generatePasswordViewModel, generatePasswordDataAccessInterface);
            return SignupView.getInstance(signupController, signupViewModel, generatePasswordViewModel, loginViewModel,
                    viewManagerModel, generatePasswordController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createSignupController(ViewManagerModel viewManagerModel,
                                                           SignupViewModel signupViewModel,
                                                           LoginViewModel loginViewModel,
                                                           SignupDataAccessInterface signupDataAccessInterface)
            throws IOException {

        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel,
                loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(signupDataAccessInterface, signupOutputBoundary,
                userFactory);

        return new SignupController(userSignupInteractor);
    }

    private static GeneratePasswordController createGeneratePasswordController(ViewManagerModel viewManagerModel,
                                                                               GeneratePasswordViewModel generatePasswordViewModel,
                                                                               GeneratePasswordDataAccessInterface generatePasswordDataAccessInterface)
            throws IOException {

        GeneratePasswordOutputBoundary generatePasswordOutputBoundary = new GenerateGeneratePasswordPresenter(
                viewManagerModel, generatePasswordViewModel);

        GeneratePasswordInputBoundary passwordGeneratorInteractor = new GeneratePasswordInteractor(
                generatePasswordDataAccessInterface, generatePasswordOutputBoundary);

        return new GeneratePasswordController(passwordGeneratorInteractor);
    }
}