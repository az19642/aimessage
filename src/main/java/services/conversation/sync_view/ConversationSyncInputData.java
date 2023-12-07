package services.conversation.sync_view;

public class ConversationSyncInputData {
    private final String contactName;

    public ConversationSyncInputData(String contactName) {
        this.contactName = contactName;
    }

    public String getContactName() {
        return contactName;
    }
}
