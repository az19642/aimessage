package use_case.translator;

import data_access.GPTDataAccessObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TranslatorInteractorTest {

    @Test
    void successTest() {
        TranslatorInputData inputData = new TranslatorInputData();
        TranslatorDataAccessInterface translatorDataAccessInterface =
                new GPTDataAccessObject(System.getenv("OPENAI_API_KEY"));

        TranslatorOutputBoundary successPresenter = new TranslatorOutputBoundary() {
            @Override
            public void prepareSuccessView(TranslatorOutputData response) {
                assertNotNull(response.getTranslatedMessage(), "Generated translation should not be null");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");
            }
        };

        TranslatorInputBoundary interactor =
                new TranslatorInteractor(translatorDataAccessInterface, successPresenter);

        String message = "What courses are you taking?";
        String targetLanguage = "French";

        interactor.execute(message, targetLanguage);
    }
}