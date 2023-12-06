package services.view_data_sync.update_contacts;

public interface LoadContactsToViewOutputBoundary {

    void prepareSuccessView(LoadContactsToViewOutputData loadContactsToViewOutputData);

    void prepareFailView();
}