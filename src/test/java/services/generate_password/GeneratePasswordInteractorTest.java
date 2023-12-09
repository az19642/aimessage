package services.generate_password;

import data_access.GPTDataAccessObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

class GeneratePasswordInteractorTest {

    @Test
    void successTest() {
        String prompt = "Generate a secure password.";
        GeneratePasswordInputData inputData = new GeneratePasswordInputData(prompt);
        GeneratePasswordDataAccessInterface generatePasswordDataAccessInterface = new GPTDataAccessObject(
                System.getenv("OPENAI_API_KEY"));

        GeneratePasswordOutputBoundary successPresenter = new GeneratePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(GeneratePasswordOutputData response) {
                assertNotNull(response.getGeneratedPassword(), "Generated password should not be null");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");
            }
        };

        GeneratePasswordInputBoundary interactor = new GeneratePasswordInteractor(generatePasswordDataAccessInterface,
                successPresenter);
        interactor.execute(inputData);
    }
}