package use_case.load_contacts_to_view;

import java.util.Map;
public class LoadContactsToViewOutputData {

    private final Map<String, String> contactToLastMessage;

    public LoadContactsToViewOutputData(Map<String, String> contactToLastMessage) {
        this.contactToLastMessage = contactToLastMessage;
    }

    public Map<String, String> getContactToLastMessage() {
        return contactToLastMessage;
    }

}