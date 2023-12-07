package services.conversation.interface_adapters;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;

/**
 * Represents the state of a conversation.
 * It contains a map of messages to their corresponding timestamps
 * as well as the contact name associated with the conversation.
 */
public class ConversationState {

    private Map<LocalDateTime, List<String>> timestampToMessage;
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
    public Map<LocalDateTime, List<String>> getTimestampToMessage() {
        return this.timestampToMessage;
    }

    public void setTimestampToMessage(Map<LocalDateTime, List<String>> timestampToMessage) {
        this.timestampToMessage = timestampToMessage;
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


}