package use_case.load_contacts_to_view;

import entity.User;

public interface LoadContactsToViewDataAccessInterface {
    User getUser(String username, String password);
}