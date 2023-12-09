package services.conversation.sync_conversation_view;

import entities.User;

public interface SyncConversationDataAccessInterface {
    User getUser();

    void syncUser();
}
