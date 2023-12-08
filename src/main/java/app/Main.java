package app;

import data_access.GPTDataAccessObject;
import data_access.MongoDataAccessObject;
import entities.CommonUserFactory;
import interface_adapters.ViewManagerModel;
import services.conversation.interface_adapters.ConversationViewModel;
import services.generate_password.interface_adapters.GeneratePasswordViewModel;
import services.logged_in.interface_adapters.LoggedInViewModel;
import services.login.interface_adapters.LoginViewModel;
import services.signup.interface_adapters.SignupViewModel;
import services.suggest_reply.interface_adapters.SuggestReplyViewModel;
import services.text_to_speech.interface_adapters.TextToSpeechViewModel;
import services.translate_message.interface_adapters.TranslateMessageViewModel;
import views.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // The main application window.
        JFrame application = new JFrame("AiMessage");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the results from the use case.
        // The ViewModels are observable, and will be observed by the Views.

        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        GeneratePasswordViewModel generatePasswordViewModel = new GeneratePasswordViewModel();
        ConversationViewModel conversationViewModel = new ConversationViewModel();
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

        application.pack();
        application.setVisible(true);
    }
}