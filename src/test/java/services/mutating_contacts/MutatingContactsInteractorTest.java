package services.mutating_contacts;

import data_access.MongoDataAccessObject;
import entities.CommonUserFactory;
import services.contact_mutation.MutatingContactsInputData;
import services.contact_mutation.MutatingContactsInteractor;
import services.contact_mutation.MutatingContactsOutputBoundary;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

public class MutatingContactsInteractorTest {
    @Test
    void execute() {
        MutatingContactsInputData inputData = new MutatingContactsInputData("test", true);
        MongoDataAccessObject mutatingContactsUserDataAccessInterface = new MongoDataAccessObject(
                System.getenv("MONGO_PASSWORD"),
                new CommonUserFactory()
        );
        mutatingContactsUserDataAccessInterface.setUser("test", "test");

        mutatingContactsUserDataAccessInterface.setUser("test", "test");

        MutatingContactsOutputBoundary mutatingContactsPresenter = new MutatingContactsOutputBoundary() {
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

        MutatingContactsInteractor interactor =
                new MutatingContactsInteractor(mutatingContactsUserDataAccessInterface, mutatingContactsPresenter);
        interactor.execute(inputData);

    }
}