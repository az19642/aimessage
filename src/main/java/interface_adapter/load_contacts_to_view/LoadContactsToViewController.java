package interface_adapter.load_contacts_to_view;

import use_case.load_contacts_to_view.LoadContactsToViewInputBoundary;
import use_case.load_contacts_to_view.LoadContactsToViewInputData;

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
     *
     * @param username the username of the user whose contacts are to be loaded.
     */
    public void execute(String username) {
        LoadContactsToViewInputData loadContactsToViewInputData = new LoadContactsToViewInputData(username);

        loadContactsToViewInteractor.execute(loadContactsToViewInputData);
    }
}