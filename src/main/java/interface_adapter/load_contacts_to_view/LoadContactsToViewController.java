package interface_adapter.load_contacts_to_view;

import use_case.load_contacts_to_view.LoadContactsToViewInputBoundary;

/**
 * Controller responsible for handling the input of the login use case and passing it to the interactor.
 */
public class LoadContactsToViewController {

    final LoadContactsToViewInputBoundary loadContactsToViewInteractor;

    /**
     * Constructor.
     *
     * @param loadContactsToViewInteractor the interactor to be used for loading contacts to view.
     */
    public LoadContactsToViewController(LoadContactsToViewInputBoundary loadContactsToViewInteractor) {
        this.loadContactsToViewInteractor = loadContactsToViewInteractor;
    }

    /**
     * Executes the use case.
     */
    public void execute() {
        loadContactsToViewInteractor.execute();
    }
}