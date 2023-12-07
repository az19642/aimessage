package services.contact.add_contact;

/**
 * Implements the mutating contacts use case.
 */
public class AddContactInteractor implements AddContactInputBoundary {

    final AddContactDataAccessInterface mutatingContactsUserDataAccessInterface;
    final AddContactOutputBoundary mutatingContactsPresenter;

    public AddContactInteractor(AddContactDataAccessInterface mutatingContactsUserDataAccessInterface,
                                AddContactOutputBoundary mutatingContactsPresenter) {
        this.mutatingContactsUserDataAccessInterface = mutatingContactsUserDataAccessInterface;
        this.mutatingContactsPresenter = mutatingContactsPresenter;
    }

    /**
     * Executes the mutating contacts use case with the provided login input data.
     *
     * @param addContactInputData The input data for the mutating contacts use case.
     */

    @Override
    public void execute(AddContactInputData addContactInputData) {
        String result = this.mutatingContactsUserDataAccessInterface.addContact(
                addContactInputData.getContactName());

        if (result.equals("PASS")) {
            mutatingContactsPresenter.prepareSuccessView();
        } else if (result.equals("USER DNE")) {
            mutatingContactsPresenter.prepareFailView("The user does not exist");
        } else if (result.equals("ALREADY A CONTACT")) {
            mutatingContactsPresenter.prepareFailView("The user is already a contact");
        } else if (result.equals("CANNOT ADD SELF")) {
            mutatingContactsPresenter.prepareFailView("Cannot add yourself as a contact");
        } else {
            // Should never enter here
            mutatingContactsPresenter.prepareFailView("Unexpected error, please try again");
        }

    }
}
