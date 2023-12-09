package views;

import app.ConversationViewFactory;
import app.LoggedInViewFactory;
import app.LoginViewFactory;
import app.SignupViewFactory;
import data_access.GPTDataAccessObject;
import data_access.MongoDataAccessObject;
import entities.CommonUserFactory;
import interface_adapters.ViewManagerModel;
import org.junit.jupiter.api.Test;
import services.conversation.interface_adapters.ConversationViewModel;
import services.generate_password.interface_adapters.GeneratePasswordViewModel;
import services.logged_in.interface_adapters.LoggedInState;
import services.logged_in.interface_adapters.LoggedInViewModel;
import services.login.LoginState;
import services.login.interface_adapters.LoginViewModel;
import services.signup.SignupState;
import services.signup.interface_adapters.SignupViewModel;
import services.suggest_reply.interface_adapters.SuggestReplyViewModel;
import services.text_to_speech.interface_adapters.TextToSpeechViewModel;
import services.translate_message.interface_adapters.TranslateMessageViewModel;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        GeneratePasswordViewModel generatePasswordViewModel = new GeneratePasswordViewModel();
        TextToSpeechViewModel textToSpeechViewModel = new TextToSpeechViewModel();
        SuggestReplyViewModel suggestReplyViewModel = new SuggestReplyViewModel();
        TranslateMessageViewModel translateMessageViewModel = new TranslateMessageViewModel();

        MongoDataAccessObject mongoDataAccessObject = new MongoDataAccessObject(System.getenv("MONGO_PASSWORD"),
                new CommonUserFactory());

        GPTDataAccessObject gptDataAccessObject;
        gptDataAccessObject = new GPTDataAccessObject(System.getenv("OPENAI_API_KEY"));

        SignupView signupView = SignupViewFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                generatePasswordViewModel, mongoDataAccessObject, gptDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginViewFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                loggedInViewModel, mongoDataAccessObject);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = LoggedInViewFactory.create(viewManagerModel, loggedInViewModel,
                conversationViewModel, mongoDataAccessObject, mongoDataAccessObject, mongoDataAccessObject);
        views.add(loggedInView, loggedInView.viewName);

        ConversationView conversationView = ConversationViewFactory.create(viewManagerModel, conversationViewModel,
                mongoDataAccessObject, mongoDataAccessObject, gptDataAccessObject, gptDataAccessObject,
                gptDataAccessObject, textToSpeechViewModel, suggestReplyViewModel, translateMessageViewModel,
                signupViewModel);
        views.add(conversationView, conversationView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        SignupState signupState = signupViewModel.getState();

        mongoDataAccessObject.deleteUser("newTest");
        // Set the test username
        signupState.setUsername(("newTest"));

        // Click the generate password button, which saves the password to the state
        assertEquals("sign up", viewManagerModel.getActiveView());
        signupView.getGeneratePasswordButton().doClick();

        // Update the state with the repeated password
        String generatedPassword = signupState.getPassword();
        signupState.setRepeatPassword(generatedPassword);


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
