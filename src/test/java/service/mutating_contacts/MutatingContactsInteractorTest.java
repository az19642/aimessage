package service.mutating_contacts;

import data_access.MongoUserDataAccessObject;
import entities.CommonUserFactory;
import features.contact_mutation.MutatingContactsInputData;
import features.contact_mutation.MutatingContactsInteractor;
import features.contact_mutation.MutatingContactsOutputBoundary;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

public class MutatingContactsInteractorTest {
    @Test
    void execute() {
        MutatingContactsInputData inputData = new MutatingContactsInputData("test", true);
        MongoUserDataAccessObject mutatingContactsUserDataAccessInterface = new MongoUserDataAccessObject(
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