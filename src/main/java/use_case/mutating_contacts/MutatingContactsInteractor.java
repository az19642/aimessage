package use_case.mutating_contacts;

import entity.User;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;

/**
 * Implements the mutating contacts use case.
 */
public class MutatingContactsInteractor implements MutatingContactsInputBoundary {

    final MutatingContactsUserDataAccessInterface mutatingContactsUserDataAccessInterface;
    final MutatingContactsOutputBoundary mutatingContactsPresenter;

    public MutatingContactsInteractor(MutatingContactsUserDataAccessInterface mutatingContactsUserDataAccessInterface,
                                      MutatingContactsOutputBoundary mutatingContactsPresenter) {
        this.mutatingContactsUserDataAccessInterface = mutatingContactsUserDataAccessInterface;
        this.mutatingContactsPresenter = mutatingContactsPresenter;
    }

    /**
     * Executes the mutating contacts use case with the provided login input data.
     *
     * @param mutatingContactsInputData The input data for the mutating contacts use case.
     */

    @Override
    public void execute(MutatingContactsInputData mutatingContactsInputData) {

        if (mutatingContactsInputData.getAddContact()) {
            String result = this.mutatingContactsUserDataAccessInterface.addContact(
                    mutatingContactsInputData.getContactName());

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

        } else {
            this.mutatingContactsUserDataAccessInterface.deleteContact(mutatingContactsInputData.getContactName());
            mutatingContactsPresenter.prepareSuccessView();
        }
    }
}
