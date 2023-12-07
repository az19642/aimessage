package services.contact.sync_contact_view;

public interface SyncContactViewOutputBoundary {

    void prepareSuccessView(SyncContactViewOutputData syncContactViewOutputData);

    void prepareFailView();
}