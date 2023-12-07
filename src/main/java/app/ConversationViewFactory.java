package app;

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
import views.ConversationView;

public class ConversationViewFactory {
    public static ConversationView create(ConversationViewModel conversationViewModel,
                                          MessageSenderUserDataAccessInterface mongoDataAccessObject,
                                          ConversationSyncDataAccessInterface conversationSyncDataAccessInterface) {

        return new ConversationView(conversationViewModel,
                createSendMessageController(mongoDataAccessObject),
                createConversationSyncController(conversationSyncDataAccessInterface,
                        conversationViewModel));
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

}
