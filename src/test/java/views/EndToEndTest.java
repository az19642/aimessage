package views;

import app.LoggedInViewFactory;
import app.LoginUseCaseFactory;
import app.SignupUseCaseFactory;
import data_access.GPTDataAccessObject;
import data_access.MongoDataAccessObject;
import entities.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import services.login.LoginState;
import services.login.interface_adapters.LoginViewModel;
import services.password_generation.interface_adapters.PasswordGeneratorViewModel;
import services.signup.SignupState;
import services.signup.interface_adapters.SignupViewModel;
import org.junit.jupiter.api.Test;
import services.logged_in.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class EndToEndTest {

    @Test
    void endToEndTest() {
        JFrame application = new JFrame("TestScreen");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        PasswordGeneratorViewModel passwordGeneratorViewModel = new PasswordGeneratorViewModel();

        MongoDataAccessObject mongoDataAccessObject = new MongoDataAccessObject(
                System.getenv("MONGO_PASSWORD"),
                new CommonUserFactory()
        );

        GPTDataAccessObject gptDataAccessObject;
        gptDataAccessObject = new GPTDataAccessObject(System.getenv("OPENAI_API_KEY"));

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                mongoDataAccessObject, passwordGeneratorViewModel, gptDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                loggedInViewModel, mongoDataAccessObject);
        views.add(loginView, loginView.viewName);

//        LoggedInView loggedInView = LoggedInViewFactory.create(loggedInViewModel, viewManagerModel, mongoDataAccessObject);
//        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        SignupState currentState = signupViewModel.getState();

        // Set the test username
        currentState.setUsername(("testUser103"));

        // Click the generate password button, which saves the password to the state
        signupView.getGeneratePasswordButton().doClick();

        // Update the state with the repeated password
        String generatedPassword = currentState.getPassword();
        currentState.setRepeatPassword(generatedPassword);

        // Sign up
        signupView.getSignUpButton().doClick();

        assertTrue(mongoDataAccessObject.userExists("testUser103"));
        assertEquals("log in", viewManagerModel.getActiveView());

        loginView.getCancelButton().doClick();
        assertEquals("sign up", viewManagerModel.getActiveView());

        signupView.getGoToLoginButton().doClick();
        assertEquals("log in", viewManagerModel.getActiveView());

        // Log in to get to contact view
        LoginState currentLogInState = loginViewModel.getState();
        currentLogInState.setPassword(generatedPassword);
        loginView.getLogInButton().doClick();
        assertEquals("logged in", viewManagerModel.getActiveView());

        mongoDataAccessObject.deleteUser("testUser103");
    }
}
