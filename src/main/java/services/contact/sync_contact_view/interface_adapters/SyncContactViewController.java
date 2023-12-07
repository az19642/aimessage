package services.contact.sync_contact_view.interface_adapters;

import services.contact.sync_contact_view.SyncContactViewInputBoundary;

/**
 * Controller responsible for handling the input of the login use case and passing it to the interactor.
 */
public class SyncContactViewController {

    final SyncContactViewInputBoundary syncContactViewInteractor;

    /**
     * Constructor.
     *
     * @param syncContactViewInteractor the interactor to be used for loading contacts to view.
     */
    public SyncContactViewController(SyncContactViewInputBoundary syncContactViewInteractor) {
        this.syncContactViewInteractor = syncContactViewInteractor;
    }

    /**
     * Executes the use case.
     */
    public void execute() {
        syncContactViewInteractor.execute();
    }
}