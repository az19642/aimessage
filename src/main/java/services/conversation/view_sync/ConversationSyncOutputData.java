package services.conversation.view_sync;

import entities.Message;

import java.util.List;

public class ConversationSyncOutputData {
    private final Map<LocalDateTime, List<String>> timestampToMessage;

    public ConversationSyncOutputData(List<Message> messages) {
        this.messages = messages;
    }
}
