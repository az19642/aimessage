package services.suggested_reply_generator;

import data_access.GPTDataAccessObject;
import services.messaging.suggest_reply_message.*;
import services.reply_suggestor.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplySuggesterInteractorTest {
    @Test
    void successTest() {
        String prompt = "Suggest a reply under 200 characters to: Who do you think is the best chess player of all time?";
        ReplySuggesterInputData inputData = new ReplySuggesterInputData(prompt);
        ReplySuggesterUserDataAccessInterface replySuggesterUserDataAccessInterface = new GPTDataAccessObject(System.getenv("OPENAI_API_KEY"));

        ReplySuggesterOutputBoundary successPresenter = new ReplySuggesterOutputBoundary() {
            @Override
            public void prepareSuccessView(ReplySuggesterOutputData response) {
                assertNotNull(response.getGeneratedReply(), "Generated Suggested Reply should not be null");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");
            }
        };

        ReplySuggesterInputBoundary interactor = new ReplySuggesterInteractor(replySuggesterUserDataAccessInterface, successPresenter);
        interactor.execute(inputData);
    }
}