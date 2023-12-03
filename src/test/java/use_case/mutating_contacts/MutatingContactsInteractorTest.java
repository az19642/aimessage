package use_case.mutating_contacts;

import data_access.GPTDataAccessObject;
import data_access.MongoUserDataAccessObject;
import entity.CommonUserFactory;
import org.junit.jupiter.api.Test;
import use_case.password_generator.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class MutatingContactsInteractorTest {
    @Test
    void execute() {
        MutatingContactsInputData inputData = new MutatingContactsInputData("test", true);
        MutatingContactsUserDataAccessInterface mutatingContactsUserDataAccessInterface = new MongoUserDataAccessObject(
                System.getenv("MONGO_PASSWORD"),
                new CommonUserFactory()
        );

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