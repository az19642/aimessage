package use_case.text_to_speech;

import data_access.GPTDataAccessObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextToSpeechInteractorTest {

    @Test
    void successTest() {
        TextToSpeechInputData inputData = new TextToSpeechInputData();
        TextToSpeechDataAccessInterface textToSpeechDataAccessInterface =
                new GPTDataAccessObject(System.getenv("OPENAI_API_KEY"));

        TextToSpeechOutputBoundary successPresenter = new TextToSpeechOutputBoundary() {
            @Override
            public void prepareSuccessView(TextToSpeechOutputData response) {
                assertTrue(response.getSpeechResult(), "Output should be true");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");
            }
        };

        TextToSpeechInputBoundary interactor =
                new TextToSpeechInteractor(textToSpeechDataAccessInterface, successPresenter);
        interactor.execute("This is a test.");
    }
}