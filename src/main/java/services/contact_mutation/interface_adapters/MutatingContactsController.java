package services.contact_mutation.interface_adapters;

import services.contact_mutation.MutatingContactsInputBoundary;
import services.contact_mutation.MutatingContactsInputData;

/**
 * Controller responsible for handling mutating contacts interactions and invoking the corresponding use case.
 */
public class MutatingContactsController {
    final MutatingContactsInputBoundary mutatingContactsInputBoundary;

    public MutatingContactsController(MutatingContactsInputBoundary mutatingContactsInputBoundary) {
        this.mutatingContactsInputBoundary = mutatingContactsInputBoundary;
    }

    /**
     * Executes the contact action with the given contactName and whether to add or delete the contact.
     * Converts the input parameters into a MutatingContactsInputData object and assigns the execution to the use case.
     *
     * @param contactName The username provided to mutate the contact.
     * @param addContact True to add the contact, and False to delete the contact,
     */
    public void execute(String contactName, boolean addContact) {
        MutatingContactsInputData mutatingContactsInputData = new MutatingContactsInputData(
                contactName, addContact);

        mutatingContactsInputBoundary.execute(mutatingContactsInputData);
    }

}
