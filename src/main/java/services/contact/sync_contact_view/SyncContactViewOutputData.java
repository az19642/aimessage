package services.contact.sync_contact_view;

import java.util.Map;

/**
 * The output data of the use case.
 */
public class SyncContactViewOutputData {

    private final Map<String, String> contactToLastMessage;

    public SyncContactViewOutputData(Map<String, String> contactToLastMessage) {
        this.contactToLastMessage = contactToLastMessage;
    }

    public Map<String, String> getContactToLastMessage() {
        return contactToLastMessage;
    }

}