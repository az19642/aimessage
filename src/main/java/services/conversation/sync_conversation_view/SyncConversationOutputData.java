package services.conversation.sync_conversation_view;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class SyncConversationOutputData {
    private final Map<LocalDateTime, List<String>> timestampToMessage;

    public SyncConversationOutputData(Map<LocalDateTime, List<String>> timestampToMessage) {
        this.timestampToMessage = timestampToMessage;
    }

    public Map<LocalDateTime, List<String>> getTimestampToMessage() {
        return timestampToMessage;
    }
}
