package views;

import app.ConversationViewFactory;
import app.LoggedInViewFactory;
import app.LoginUseCaseFactory;
import app.SignupUseCaseFactory;
import data_access.GPTDataAccessObject;
import data_access.MongoDataAccessObject;
import entities.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import services.conversation.interface_adapters.ConversationViewModel;
import services.logged_in.LoggedInState;
import services.login.LoginState;
import services.login.interface_adapters.LoginViewModel;
import services.password_generation.interface_adapters.PasswordGeneratorViewModel;
import services.signup.SignupState;
import services.signup.interface_adapters.SignupViewModel;
import org.junit.jupiter.api.Test;
import services.logged_in.LoggedInViewModel;
import services.suggest_reply.interface_adapters.ReplySuggesterViewModel;
import services.text_to_speech.interface_adapters.TextToSpeechViewModel;
import services.translate_message.interface_adapters.MessageTranslatorViewModel;

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
        ConversationViewModel conversationViewModel = new ConversationViewModel();
        PasswordGeneratorViewModel passwordGeneratorViewModel = new PasswordGeneratorViewModel();
        TextToSpeechViewModel textToSpeechViewModel = new TextToSpeechViewModel();
        ReplySuggesterViewModel replySuggesterViewModel = new ReplySuggesterViewModel();
        MessageTranslatorViewModel messageTranslatorViewModel = new MessageTranslatorViewModel();

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

        LoggedInView loggedInView = LoggedInViewFactory.create(viewManagerModel, loggedInViewModel,
                conversationViewModel ,mongoDataAccessObject, mongoDataAccessObject, mongoDataAccessObject);
        views.add(loggedInView, loggedInView.viewName);

        ConversationView conversationView = ConversationViewFactory.create(viewManagerModel, conversationViewModel,
                mongoDataAccessObject, mongoDataAccessObject, gptDataAccessObject, textToSpeechViewModel,
                replySuggesterViewModel, messageTranslatorViewModel, signupViewModel);
        views.add(conversationView, conversationView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        SignupState currentState = signupViewModel.getState();

        mongoDataAccessObject.deleteUser("newTest");
        // Set the test username
        currentState.setUsername(("newTest"));

        // Click the generate password button, which saves the password to the state
        assertEquals("sign up", viewManagerModel.getActiveView());
        signupView.getGeneratePasswordButton().doClick();

        // Update the state with the repeated password
        String generatedPassword = currentState.getPassword();
        currentState.setRepeatPassword(generatedPassword);


        // Sign up a new user
        assertEquals("Generate a secure password.", viewManagerModel.getActiveView());
        assertFalse(mongoDataAccessObject.userExists("newTest"));

        signupView.getSignUpButton().doClick();

        // Ensure the user did not exist should move to log in
        assertEquals("log in", viewManagerModel.getActiveView());

        // Log in to get to contact view
        LoginState currentLogInState = loginViewModel.getState();
        currentLogInState.setPassword(generatedPassword);
        loginView.getLogInButton().doClick();

        // Logged in
        assertEquals("logged in", viewManagerModel.getActiveView());

        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername("c");

        loggedInView.getAddButton().doClick();
        loggedInView.getSyncButton().doClick();

        conversationView.getBackButton().doClick();

        mongoDataAccessObject.deleteUser("newTest");
    }
}
