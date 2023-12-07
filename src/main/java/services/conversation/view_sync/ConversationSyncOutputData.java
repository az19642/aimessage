package services.conversation.view_sync;

import entities.Message;

import java.util.List;

public class ConversationSyncOutputData {
    private final List<Message> messages;

    public ConversationSyncOutputData(List<Message> messages) {
        this.messages = messages;
    }
}
