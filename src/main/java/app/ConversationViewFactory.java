package app;

import services.conversation.interface_adapters.ConversationViewModel;
import services.conversation.view_sync.ConversationSyncInteractor;
import services.conversation.view_sync.interface_adapters.ConversationSyncController;
import services.conversation.view_sync.interface_adapters.ConversationSyncPresenter;
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

    public static ConversationSyncController createConversationSyncController() {
        ConversationSyncInteractor conversationSyncInteractor = new ConversationSyncInteractor();
        ConversationSyncPresenter conversationSyncPresenter = new ConversationSyncPresenter();
        return new ConversationSyncController();
    }

}
