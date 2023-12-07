package services.conversation.sync_conversation_view;

public class ConversationSyncInputData {
    private final String contactName;

    public ConversationSyncInputData(String contactName) {
        this.contactName = contactName;
    }

    public String getContactName() {
        return contactName;
    }
}
