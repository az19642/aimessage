package services.suggest_reply;

import data_access.GPTDataAccessObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

class SuggestReplyInteractorTest {
    @Test
    void successTest() {
        String prompt = "Suggest a reply under 200 characters to: Who do you think is the best chess player of all time?";
        SuggestReplyInputData inputData = new SuggestReplyInputData(prompt);
        SuggestReplyDataAccessInterface suggestReplyDataAccessInterface = new GPTDataAccessObject(
                System.getenv("OPENAI_API_KEY"));

        SuggestReplyOutputBoundary successPresenter = new SuggestReplyOutputBoundary() {
            @Override
            public void prepareSuccessView(SuggestReplyOutputData response) {
                assertNotNull(response.getGeneratedReply(), "Generated Suggested Reply should not be null");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");
            }
        };

        SuggestReplyInputBoundary interactor = new SuggestReplyInteractor(suggestReplyDataAccessInterface,
                successPresenter);
        interactor.execute(inputData);
    }
}