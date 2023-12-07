package services.contacts_view_sync;

public interface LoadContactsToViewOutputBoundary {

    void prepareSuccessView(LoadContactsToViewOutputData loadContactsToViewOutputData);

    void prepareFailView();
}