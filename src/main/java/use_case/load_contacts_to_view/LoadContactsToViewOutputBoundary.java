package use_case.load_contacts_to_view;

public interface LoadContactsToViewOutputBoundary {

    void prepareSuccessView(LoadContactsToViewOutputData loadContactsToViewOutputData);

    void prepareFailView();
}