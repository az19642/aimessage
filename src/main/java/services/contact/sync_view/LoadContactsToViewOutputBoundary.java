package services.contact.sync_view;

public interface LoadContactsToViewOutputBoundary {

    void prepareSuccessView(LoadContactsToViewOutputData loadContactsToViewOutputData);

    void prepareFailView();
}