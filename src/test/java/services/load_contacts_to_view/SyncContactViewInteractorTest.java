package services.load_contacts_to_view;

import entities.*;
import services.contact.sync_contact_view.SyncContactViewDataAccessInterface;
import services.contact.sync_contact_view.SyncContactViewInteractor;
import services.contact.sync_contact_view.SyncContactViewOutputBoundary;
import services.contact.sync_contact_view.SyncContactViewOutputData;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Test class for the LoadContactsToViewInteractor class.
 */
public class SyncContactViewInteractorTest {

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
        SyncContactViewDataAccessInterface mockDataAccessObject = new SyncContactViewDataAccessInterface() {
            @Override
            public User getUser() {
                return user;
            }

            @Override
            public void syncUser() {
            }
        };
        SyncContactViewOutputBoundary loadContactsToViewPresenter = new SyncContactViewOutputBoundary() {
            @Override
            public void prepareSuccessView(SyncContactViewOutputData loadContactsToViewOutputData) {
                assert loadContactsToViewOutputData.getContactToLastMessage().get("Contact1").equals("Hello, this is " +
                        "User!");
                assert loadContactsToViewOutputData.getContactToLastMessage().size() == 1;
            }

            @Override
            public void prepareFailView() {
                // should not occur
            }
        };
        SyncContactViewInteractor syncContactViewInteractor =
                new SyncContactViewInteractor(loadContactsToViewPresenter, mockDataAccessObject);
        syncContactViewInteractor.execute();

    }

}
