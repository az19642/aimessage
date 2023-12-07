package app;

import data_access.GPTDataAccessObject;
import interface_adapter.ViewManagerModel;
import services.conversation.interface_adapters.ConversationViewModel;
import services.conversation.sync_conversation_view.ConversationSyncDataAccessInterface;
import services.conversation.sync_conversation_view.ConversationSyncInteractor;
import services.conversation.sync_conversation_view.interface_adapters.ConversationSyncController;
import services.conversation.sync_conversation_view.interface_adapters.ConversationSyncPresenter;
import services.send_message.MessageSenderInputBoundary;
import services.send_message.MessageSenderInteractor;
import services.send_message.MessageSenderOutputBoundary;
import services.send_message.MessageSenderUserDataAccessInterface;
import services.send_message.interface_adapters.MessageSenderController;
import services.send_message.interface_adapters.MessageSenderPresenter;
import services.signup.interface_adapters.SignupViewModel;
import services.suggest_reply.ReplySuggesterInputBoundary;
import services.suggest_reply.ReplySuggesterInteractor;
import services.suggest_reply.interface_adapters.ReplySuggesterController;
import services.suggest_reply.interface_adapters.ReplySuggesterPresenter;
import services.suggest_reply.interface_adapters.ReplySuggesterViewModel;
import services.text_to_speech.TextToSpeechInputBoundary;
import services.text_to_speech.TextToSpeechInteractor;
import services.text_to_speech.interface_adapters.TextToSpeechController;
import services.text_to_speech.interface_adapters.TextToSpeechPresenter;
import services.text_to_speech.interface_adapters.TextToSpeechViewModel;
import services.translate_message.TranslatorInputBoundary;
import services.translate_message.TranslatorInteractor;
import services.translate_message.interface_adapters.MessageTranslatorController;
import services.translate_message.interface_adapters.MessageTranslatorPresenter;
import services.translate_message.interface_adapters.MessageTranslatorViewModel;
import views.ConversationView;
import views.SignupView;

public class ConversationViewFactory {
    public static ConversationView create(ViewManagerModel viewManagerModel,
                                          ConversationViewModel conversationViewModel,
                                          MessageSenderUserDataAccessInterface mongoDataAccessObject,
                                          ConversationSyncDataAccessInterface conversationSyncDataAccessInterface,
                                          GPTDataAccessObject gptDataAccessObject,
                                          TextToSpeechViewModel textToSpeechViewModel,
                                          ReplySuggesterViewModel replySuggesterViewModel,
                                          MessageTranslatorViewModel messageTranslatorViewModel,
                                          SignupViewModel signupViewModel) {

        return new ConversationView(conversationViewModel,
                createSendMessageController(mongoDataAccessObject),
                createConversationSyncController(conversationSyncDataAccessInterface,
                        conversationViewModel),
                createTextToSpeechController(gptDataAccessObject, textToSpeechViewModel, viewManagerModel),
                createReplySuggesterController(gptDataAccessObject, replySuggesterViewModel, viewManagerModel),
                createMessageTranslatorController(gptDataAccessObject, messageTranslatorViewModel, viewManagerModel),
                replySuggesterViewModel,
                messageTranslatorViewModel,
                signupViewModel,
                viewManagerModel);
    }

    public static MessageSenderController createSendMessageController(MessageSenderUserDataAccessInterface mongoDataAccessObject) {
        MessageSenderOutputBoundary sendMessagePresenter = new MessageSenderPresenter();
        MessageSenderInputBoundary sendMessageInteractor = new MessageSenderInteractor(mongoDataAccessObject,
                sendMessagePresenter);
        return new MessageSenderController(sendMessageInteractor);
    }

    public static ConversationSyncController createConversationSyncController(
            ConversationSyncDataAccessInterface mongoDataAccessObject, ConversationViewModel conversationViewModel) {

        ConversationSyncPresenter conversationSyncPresenter = new ConversationSyncPresenter(conversationViewModel);
        ConversationSyncInteractor conversationSyncInteractor = new ConversationSyncInteractor(mongoDataAccessObject,
                conversationSyncPresenter);

        return new ConversationSyncController(conversationSyncInteractor);
    }

    public static TextToSpeechController createTextToSpeechController(
            GPTDataAccessObject gptDataAccessObject, TextToSpeechViewModel textToSpeechViewModel,
            ViewManagerModel viewManagerModel) {

        TextToSpeechPresenter textToSpeechPresenter = new TextToSpeechPresenter(viewManagerModel, textToSpeechViewModel);
        TextToSpeechInputBoundary textToSpeechInteractor = new TextToSpeechInteractor(gptDataAccessObject,
                textToSpeechPresenter);

        return new TextToSpeechController(textToSpeechInteractor);
    }

    public static ReplySuggesterController createReplySuggesterController(
            GPTDataAccessObject gptDataAccessObject, ReplySuggesterViewModel replySuggesterViewModel,
            ViewManagerModel viewManagerModel) {

        ReplySuggesterPresenter replySuggesterPresenter = new ReplySuggesterPresenter(replySuggesterViewModel, viewManagerModel);
        ReplySuggesterInputBoundary replySuggesterInteractor = new ReplySuggesterInteractor(gptDataAccessObject,
                replySuggesterPresenter);

        return new ReplySuggesterController(replySuggesterInteractor);
    }

    public static MessageTranslatorController createMessageTranslatorController(
            GPTDataAccessObject gptDataAccessObject, MessageTranslatorViewModel messageTranslatorViewModel,
            ViewManagerModel viewManagerModel) {

        MessageTranslatorPresenter messageTranslatorPresenter = new MessageTranslatorPresenter(viewManagerModel, messageTranslatorViewModel);
        TranslatorInputBoundary messageTranslatorInteractor = new TranslatorInteractor(gptDataAccessObject,
                messageTranslatorPresenter);

        return new MessageTranslatorController(messageTranslatorInteractor);
    }

}
