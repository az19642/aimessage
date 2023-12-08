package services.translator;

import data_access.GPTDataAccessObject;
import data_access.MongoDataAccessObject;
import entities.CommonUserFactory;
import services.translate_message.*;
import org.junit.jupiter.api.Test;
import services.translate_message.*;

import static org.junit.jupiter.api.Assertions.*;

class TranslatorInteractorTest {

    @Test
    void successTest() {
        String message = "What courses are you taking?";
        String targetLanguage = "French";

        TranslatorInputData inputData = new TranslatorInputData(message);
        TranslatorDataAccessInterface translatorDataAccessInterface =
                new GPTDataAccessObject(System.getenv("OPENAI_API_KEY"));

        MongoDataAccessObject translatorDataMongoAccessInterface = new MongoDataAccessObject(
                System.getenv("MONGO_PASSWORD"),
                new CommonUserFactory()
        );

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
                new TranslatorInteractor(translatorDataAccessInterface,
                        translatorDataMongoAccessInterface, successPresenter);

        interactor.execute(inputData);
    }
}