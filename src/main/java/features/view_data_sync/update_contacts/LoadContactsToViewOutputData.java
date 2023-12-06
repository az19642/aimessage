package features.view_data_sync.update_contacts;

import java.util.Map;

/**
 * The output data of the use case.
 */
public class LoadContactsToViewOutputData {

    private final Map<String, String> contactToLastMessage;

    public LoadContactsToViewOutputData(Map<String, String> contactToLastMessage) {
        this.contactToLastMessage = contactToLastMessage;
    }

    public Map<String, String> getContactToLastMessage() {
        return contactToLastMessage;
    }

}