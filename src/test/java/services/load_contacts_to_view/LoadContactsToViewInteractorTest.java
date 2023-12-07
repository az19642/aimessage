package services.load_contacts_to_view;

import entities.*;
import services.contacts_view_sync.LoadContactsToViewDataAccessInterface;
import services.contacts_view_sync.LoadContactsToViewInteractor;
import services.contacts_view_sync.LoadContactsToViewOutputBoundary;
import services.contacts_view_sync.LoadContactsToViewOutputData;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Test class for the LoadContactsToViewInteractor class.
 */
public class LoadContactsToViewInteractorTest {

    /**
     * Tests the execute method of the LoadContactsToViewInteractor class.
     */
    @Test
    void successTest() {
        UserFactory commonUserFactory = new CommonUserFactory();
        ContactFactory contactFactory = new ContactFactory();
        MessageFactory messageFactory = new MessageFactory();

        Message message = messageFactory.create("Hello, this is User!",
                "User",
                LocalDateTime.now()
        );

        Contact contact = contactFactory.create("Contact1",
                "EN",
                LocalDateTime.now(),
                List.of(message)
        );

        User user = commonUserFactory.create("User",
                "Password",
                "EN",
                LocalDateTime.now(),
                List.of(contact)
        );
        LoadContactsToViewDataAccessInterface mockDataAccessObject = new LoadContactsToViewDataAccessInterface() {
            @Override
            public User getUser() {
                return user;
            }
        };
        LoadContactsToViewOutputBoundary loadContactsToViewPresenter = new LoadContactsToViewOutputBoundary() {
            @Override
            public void prepareSuccessView(LoadContactsToViewOutputData loadContactsToViewOutputData) {
                assert loadContactsToViewOutputData.getContactToLastMessage().get("Contact1").equals("Hello, this is " +
                        "User!");
                assert loadContactsToViewOutputData.getContactToLastMessage().size() == 1;
            }

            @Override
            public void prepareFailView() {
                // should not occur
            }
        };
        LoadContactsToViewInteractor loadContactsToViewInteractor =
                new LoadContactsToViewInteractor(loadContactsToViewPresenter, mockDataAccessObject);
        loadContactsToViewInteractor.execute();

    }

}
