package use_case.password_generator;

import data_access.GPTDataAccessObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorInteractorTest {

    @Test
    void successTest() {
        PasswordGeneratorInputData inputData = new PasswordGeneratorInputData();
        PasswordGeneratorUserDataAccessInterface passwordGeneratorUserDataAccessInterface =
                new GPTDataAccessObject(System.getenv("OPENAI_API_KEY"));

        PasswordGeneratorOutputBoundary successPresenter = new PasswordGeneratorOutputBoundary() {
            @Override
            public void prepareSuccessView(PasswordGeneratorOutputData response) {
                assertNotNull(response.getGeneratedPassword(), "Generated password should not be null");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");
            }
        };

        PasswordGeneratorInputBoundary interactor =
                new PasswordGeneratorInteractor(passwordGeneratorUserDataAccessInterface, successPresenter);
        interactor.execute("Generate a secure password.");
    }
}