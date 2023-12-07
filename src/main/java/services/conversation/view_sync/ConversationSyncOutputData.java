package services.conversation.view_sync;

import entities.Message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ConversationSyncOutputData {
    private final Map<LocalDateTime, List<String>> timestampToMessage;

    public ConversationSyncOutputData(Map<LocalDateTime, List<String>> timestampToMessage) {
        this.timestampToMessage = timestampToMessage;
    }

    public Map<LocalDateTime, List<String>> getTimestampToMessage() {
        return timestampToMessage;
    }
}
