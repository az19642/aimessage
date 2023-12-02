package use_case.load_contacts_to_view;

public class LoadContactsToViewInputData {

    final private String username;

    public LoadContactsToViewInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }

}