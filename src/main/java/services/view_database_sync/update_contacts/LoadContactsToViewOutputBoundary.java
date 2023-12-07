package services.view_database_sync.update_contacts;

public interface LoadContactsToViewOutputBoundary {

    void prepareSuccessView(LoadContactsToViewOutputData loadContactsToViewOutputData);

    void prepareFailView();
}