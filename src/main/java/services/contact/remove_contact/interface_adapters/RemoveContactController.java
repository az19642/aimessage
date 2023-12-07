package services.contact.remove_contact.interface_adapters;


import services.contact.add_contact.AddContactInputBoundary;
import services.contact.remove_contact.RemoveContactInputBoundary;
import services.contact.remove_contact.RemoveContactInputData;

/**
 * Controller responsible for handling mutating contacts interactions and invoking the corresponding use case.
 */
public class RemoveContactController {
    final RemoveContactInputBoundary removeContactInputBoundary;

    public RemoveContactController(RemoveContactInputBoundary removeContactInputBoundary) {
        this.removeContactInputBoundary = removeContactInputBoundary;
    }

    /**
     * Executes the contact action with the given contactName and whether to add or delete the contact.
     * Converts the input parameters into a MutatingContactsInputData object and assigns the execution to the use case.
     *
     * @param contactName The username provided to mutate the contact.
     */
    public void execute(String contactName) {
        RemoveContactInputData removeContactInputData = new RemoveContactInputData(contactName);

        removeContactInputBoundary.execute(removeContactInputData);
    }

}