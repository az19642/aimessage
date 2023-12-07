package services.contact.remove_contact;

/**
 * Interactor for the remove contact use case.
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
     * Executes the remove contact use case with the provided remove contact input data.
     *
     * @param removeContactInputData The input data for the remove contact use case.
     */
    @Override
    public void execute(RemoveContactInputData removeContactInputData) {
        this.removeContactDataAccessInterface.removeContact(removeContactInputData.getContactName());
        mutatingContactsPresenter.prepareSuccessView();

    }
}
