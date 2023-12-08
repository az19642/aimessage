package app;

import interface_adapters.ViewManagerModel;
import services.conversation.interface_adapters.ConversationViewModel;
import services.conversation.send_message.SendMessageDataAccessInterface;
import services.conversation.send_message.SendMessageInputBoundary;
import services.conversation.send_message.SendMessageInteractor;
import services.conversation.send_message.SendMessageOutputBoundary;
import services.conversation.send_message.interface_adapters.SendMessageController;
import services.conversation.send_message.interface_adapters.SendSendMessagePresenter;
import services.conversation.sync_conversation_view.SyncConversationDataAccessInterface;
import services.conversation.sync_conversation_view.SyncConversationInteractor;
import services.conversation.sync_conversation_view.interface_adapters.SyncConversationController;
import services.conversation.sync_conversation_view.interface_adapters.SyncConversationPresenter;
import services.signup.interface_adapters.SignupViewModel;
import services.suggest_reply.SuggestReplyDataAccessInterface;
import services.suggest_reply.SuggestReplyInputBoundary;
import services.suggest_reply.SuggestReplyInteractor;
import services.suggest_reply.interface_adapters.SuggestReplyController;
import services.suggest_reply.interface_adapters.SuggestReplyViewModel;
import services.suggest_reply.interface_adapters.SuggestSuggestReplyPresenter;
import services.text_to_speech.TextToSpeechDataAccessInterface;
import services.text_to_speech.TextToSpeechInputBoundary;
import services.text_to_speech.TextToSpeechInteractor;
import services.text_to_speech.interface_adapters.TextToSpeechController;
import services.text_to_speech.interface_adapters.TextToSpeechPresenter;
import services.text_to_speech.interface_adapters.TextToSpeechViewModel;
import services.translate_message.TranslateMessageDataAccessInterface;
import services.translate_message.TranslateMessageInputBoundary;
import services.translate_message.TranslateMessageInteractor;
import services.translate_message.interface_adapters.TranslateMessageController;
import services.translate_message.interface_adapters.TranslateMessagePresenter;
import services.translate_message.interface_adapters.TranslateMessageViewModel;
import views.ConversationView;

/**
 * Factory for creating the conversation view.
 */
public class ConversationViewFactory {

    private ConversationViewFactory() {
    }

    /**
     * Creates a new instance of the conversation view.
     *
     * @param viewManagerModel                    The view manager model.
     * @param conversationViewModel               The conversation view model.
     * @param sendMessageDataAccessInterface      The data access object for sending messages.
     * @param syncConversationDataAccessInterface The data access object for syncing conversations.
     * @param textToSpeechDataAccessInterface     The data access object for text to speech.
     * @param suggestReplyDataAccessInterface     The data access object for suggesting replies.
     * @param translateMessageDataAccessInterface The data access object for translating messages.
     * @param textToSpeechViewModel               The text to speech view model.
     * @param suggestReplyViewModel               The suggest reply view model.
     * @param translateMessageViewModel           The translate message view model.
     * @param signupViewModel                     The signup view model.
     * @return The conversation view.
     */
    public static ConversationView create(ViewManagerModel viewManagerModel,
                                          ConversationViewModel conversationViewModel,
                                          SendMessageDataAccessInterface sendMessageDataAccessInterface,
                                          SyncConversationDataAccessInterface syncConversationDataAccessInterface,
                                          TextToSpeechDataAccessInterface textToSpeechDataAccessInterface,
                                          SuggestReplyDataAccessInterface suggestReplyDataAccessInterface,
                                          TranslateMessageDataAccessInterface translateMessageDataAccessInterface,
                                          TextToSpeechViewModel textToSpeechViewModel,
                                          SuggestReplyViewModel suggestReplyViewModel,
                                          TranslateMessageViewModel translateMessageViewModel,
                                          SignupViewModel signupViewModel) {
        SendMessageController sendMessageController = createSendMessageController(sendMessageDataAccessInterface);

        SyncConversationController syncConversationController = createSyncConversationController(
                syncConversationDataAccessInterface, conversationViewModel);

        TextToSpeechController textToSpeechController = createTextToSpeechController(textToSpeechDataAccessInterface,
                textToSpeechViewModel, viewManagerModel);

        SuggestReplyController suggestReplyController = createSuggestReplyController(suggestReplyDataAccessInterface,
                suggestReplyViewModel, viewManagerModel);

        TranslateMessageController translateMessageController = createTranslateMessageController(
                translateMessageDataAccessInterface, translateMessageViewModel, viewManagerModel);

        return ConversationView.getInstance(conversationViewModel, sendMessageController, syncConversationController,
                textToSpeechController, suggestReplyController, translateMessageController, suggestReplyViewModel,
                translateMessageViewModel, signupViewModel, viewManagerModel);
    }

    private static SendMessageController createSendMessageController(
            SendMessageDataAccessInterface sendMessageDataAccessInterface) {
        SendMessageOutputBoundary sendMessagePresenter = new SendSendMessagePresenter();

        SendMessageInputBoundary sendMessageInteractor = new SendMessageInteractor(sendMessageDataAccessInterface,
                sendMessagePresenter);
        return new SendMessageController(sendMessageInteractor);
    }

    private static SyncConversationController createSyncConversationController(
            SyncConversationDataAccessInterface syncConversationDataAccessInterface,
            ConversationViewModel conversationViewModel) {

        SyncConversationPresenter syncConversationPresenter = new SyncConversationPresenter(conversationViewModel);

        SyncConversationInteractor conversationSyncInteractor = new SyncConversationInteractor(
                syncConversationDataAccessInterface, syncConversationPresenter);

        return new SyncConversationController(conversationSyncInteractor);
    }

    private static TextToSpeechController createTextToSpeechController(
            TextToSpeechDataAccessInterface textToSpeechDataAccessInterface,
            TextToSpeechViewModel textToSpeechViewModel, ViewManagerModel viewManagerModel) {

        TextToSpeechPresenter textToSpeechPresenter = new TextToSpeechPresenter(viewManagerModel,
                textToSpeechViewModel);

        TextToSpeechInputBoundary textToSpeechInteractor = new TextToSpeechInteractor(textToSpeechDataAccessInterface,
                textToSpeechPresenter);

        return new TextToSpeechController(textToSpeechInteractor);
    }

    private static SuggestReplyController createSuggestReplyController(
            SuggestReplyDataAccessInterface suggestReplyDataAccessInterface,
            SuggestReplyViewModel suggestReplyViewModel, ViewManagerModel viewManagerModel) {

        SuggestSuggestReplyPresenter suggestReplyPresenter = new SuggestSuggestReplyPresenter(suggestReplyViewModel,
                viewManagerModel);
        SuggestReplyInputBoundary replySuggesterInteractor = new SuggestReplyInteractor(suggestReplyDataAccessInterface,
                suggestReplyPresenter);

        return new SuggestReplyController(replySuggesterInteractor);
    }

    private static TranslateMessageController createTranslateMessageController(
            TranslateMessageDataAccessInterface translateMessageDataAccessInterface,
            TranslateMessageViewModel translateMessageViewModel, ViewManagerModel viewManagerModel) {

        TranslateMessagePresenter messageTranslatorPresenter = new TranslateMessagePresenter(viewManagerModel,
                translateMessageViewModel);
        TranslateMessageInputBoundary messageTranslatorInteractor = new TranslateMessageInteractor(
                translateMessageDataAccessInterface, messageTranslatorPresenter);

        return new TranslateMessageController(messageTranslatorInteractor);
    }

}
