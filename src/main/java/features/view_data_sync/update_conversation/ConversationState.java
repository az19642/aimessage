package features.view_data_sync.update_conversation;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents the state of a conversation.
 * It contains a map of messages to their corresponding timestamps
 * as well as the contact name associated with the conversation.
 */
public class ConversationState {
    private List<MessageMetaData> timestampToMessage;
    private String contactName = "";

    public ConversationState(ConversationState copy) {
        timestampToMessage = copy.timestampToMessage;
    }

    public ConversationState() {
    }

    /**
     * Returns a map of messages to their corresponding timestamps in the conversation view state.
     *
     * @return A map where the keys are timestamps and the values are the messages.
     */
    public List<MessageMetaData> getTimestampToMessage() {
        return this.timestampToMessage;
    }

    /**
     * Gets the contact name associated with the conversation state.
     *
     * @return The contact name.
     */
    public String getContactName() {
        return this.contactName;
    }

    /**
     * Sets the contact name for the conversation state.
     *
     * @param contactName The new contact name to set.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }



    class MessageMetaData {
        private final String sender;
        private final String content;
        private final LocalDateTime messageTime;

        public MessageMetaData(String sender, String content, LocalDateTime messageTime) {
            this.sender = sender;
            this.content = content;
            this.messageTime = messageTime;
        }
    }

}