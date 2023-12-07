package services.contact.add_contact.interface_adapters;


import services.contact.add_contact.AddContactInputBoundary;
import services.contact.add_contact.AddContactInputData;

/**
 * Controller responsible for handling mutating contacts interactions and invoking the corresponding use case.
 */
public class AddContactController {
    final AddContactInputBoundary addContactInputBoundary;

    public AddContactController(AddContactInputBoundary addContactInputBoundary) {
        this.addContactInputBoundary = addContactInputBoundary;
    }

    /**
     * Executes the contact action with the given contactName and whether to add or delete the contact.
     * Converts the input parameters into a MutatingContactsInputData object and assigns the execution to the use case.
     *
     * @param contactName The username provided to mutate the contact.
     */
    public void execute(String contactName) {
        AddContactInputData addContactInputData = new AddContactInputData(contactName);

        addContactInputBoundary.execute(addContactInputData);
    }

}