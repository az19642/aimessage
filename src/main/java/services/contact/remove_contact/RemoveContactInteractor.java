package services.contact.remove_contact;

/**
 * Implements the mutating contacts use case.
 */
public class RemoveContactInteractor implements RemoveContactInputBoundary {

    final RemoveContactDataAccessInterface removeContactDataAccessInterface;
    final RemoveContactOutputBoundary mutatingContactsPresenter;

    public RemoveContactInteractor(RemoveContactDataAccessInterface removeContactDataAccessInterface,
                                   RemoveContactOutputBoundary mutatingContactsPresenter) {
        this.removeContactDataAccessInterface = removeContactDataAccessInterface;
        this.mutatingContactsPresenter = mutatingContactsPresenter;
    }

    /**
     * Executes the mutating contacts use case with the provided login input data.
     *
     * @param removeContactInputData The input data for the mutating contacts use case.
     */

    @Override
    public void execute(RemoveContactInputData removeContactInputData) {
        this.removeContactDataAccessInterface.deleteContact(removeContactInputData.getContactName());
        mutatingContactsPresenter.prepareSuccessView();

    }
}
