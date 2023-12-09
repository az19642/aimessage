package services.translate_message;

import data_access.GPTDataAccessObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

class TranslateMessageInteractorTest {

    @Test
    void successTest() {
        String message = "What courses are you taking?";
        String targetLanguage = "French";

        TranslateMessageInputData inputData = new TranslateMessageInputData(message, targetLanguage);
        TranslateMessageDataAccessInterface translateMessageDataAccessInterface = new GPTDataAccessObject(
                System.getenv("OPENAI_API_KEY"));

        TranslateMessageOutputBoundary successPresenter = new TranslateMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(TranslateMessageOutputData response) {
                assertNotNull(response.getTranslatedMessage(), "Generated translation should not be null");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");
            }
        };

        TranslateMessageInputBoundary interactor = new TranslateMessageInteractor(translateMessageDataAccessInterface,
                successPresenter);

        interactor.execute(inputData);
    }
}