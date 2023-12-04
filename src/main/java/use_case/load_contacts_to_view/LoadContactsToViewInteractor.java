package use_case.load_contacts_to_view;

import java.util.HashMap;
import java.util.Map;

/**
 * Interactor responsible for handling the input of the use case.
 */
public class LoadContactsToViewInteractor implements LoadContactsToViewInputBoundary {
    final LoadContactsToViewDataAccessInterface userDataAccessObject;
    final LoadContactsToViewOutputBoundary updateLoggedInStatePresenter;

    public LoadContactsToViewInteractor(LoadContactsToViewOutputBoundary updateLoggedInStatePresenter,
                                        LoadContactsToViewDataAccessInterface dataAccessObject) {
        this.userDataAccessObject = dataAccessObject;
        this.updateLoggedInStatePresenter = updateLoggedInStatePresenter;
    }

    /**
     * This method is called by the controller to execute the use case.
     * @param loadContactsToViewInputData the input data for the use case.
     */

    @Override
    public void execute(LoadContactsToViewInputData loadContactsToViewInputData) {
        Map<String, String> contactToMessage = new HashMap<>(); // TODO use DAO to update this accordingly
        contactToMessage.put("Andy", "Hello, this is the first message!");
        contactToMessage.put("Chris", "This UI looks amazing guys");
        contactToMessage.put("Jonathan", "Test.");
        contactToMessage.put("a", "Hello, this is the first message!");
        contactToMessage.put("b", "This UI looks amazing guys");
        contactToMessage.put("c", "Test 2.");
        contactToMessage.put("d", "Hello, this is the first message!");
        contactToMessage.put("e", "This UI looks amazing guys");
        contactToMessage.put("f", "sup.");
        contactToMessage.put("g", "Hello, this is the first message!");
        contactToMessage.put("h", "This UI looks amazing guys");
        contactToMessage.put("i", "hello.");
        LoadContactsToViewOutputData loadContactsToViewOutputData = new LoadContactsToViewOutputData(contactToMessage);
        updateLoggedInStatePresenter.prepareSuccessView(loadContactsToViewOutputData);
    }
}