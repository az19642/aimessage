package services.mutating_contacts;

import data_access.MongoDataAccessObject;
import entities.CommonUserFactory;
import services.contact.add_contact.AddContactInputData;
import services.contact.add_contact.AddContactInteractor;
import services.contact.add_contact.AddContactOutputBoundary;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

public class MutatingContactsInteractorTest {
    @Test
    void execute() {
        AddContactInputData inputData = new AddContactInputData("test");
        MongoDataAccessObject mutatingContactsUserDataAccessInterface = new MongoDataAccessObject(
                System.getenv("MONGO_PASSWORD"),
                new CommonUserFactory()
        );
        mutatingContactsUserDataAccessInterface.setUser("test", "test");

        mutatingContactsUserDataAccessInterface.setUser("test", "test");

        AddContactOutputBoundary mutatingContactsPresenter = new AddContactOutputBoundary() {
            @Override
            public void prepareSuccessView() {

            }
            @Override
            public void prepareFailView(String error) {

                if (error.equals("Unexpected error, please try again")) {
                    fail("Use case failure is unexpected");
                }
            }
        };

        AddContactInteractor interactor =
                new AddContactInteractor(mutatingContactsUserDataAccessInterface, mutatingContactsPresenter);
        interactor.execute(inputData);

    }
}