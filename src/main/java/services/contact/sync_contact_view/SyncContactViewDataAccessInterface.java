package services.contact.sync_contact_view;

import entities.User;

public interface SyncContactViewDataAccessInterface {
    User getUser();

    void syncUser();
}