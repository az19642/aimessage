package services.conversation.sync_conversation_view;

public class SyncConversationInputData {
    private final String contactName;

    public SyncConversationInputData(String contactName) {
        this.contactName = contactName;
    }

    public String getContactName() {
        return contactName;
    }
}
