package services.suggest_reply.interface_adapters;

import services.suggest_reply.SuggestReplyInputBoundary;
import services.suggest_reply.SuggestReplyInputData;

/**
 * The controller class responsible for executing the Suggested Reply Generator use case.
 */
public class SuggestReplyController {
    private final SuggestReplyInputBoundary replySuggesterUseCaseInteractor;

    /**
     * Constructs a new ReplySuggesterController with the provided ReplySuggesterInputBoundary.
     *
     * @param replySuggesterUseCaseInteractor The interactor responsible for generating suggested replies.
     */
    public SuggestReplyController(SuggestReplyInputBoundary replySuggesterUseCaseInteractor) {
        this.replySuggesterUseCaseInteractor = replySuggesterUseCaseInteractor;
    }

    /**
     * Executes the Suggested Reply Generator use case to generate a suggested reply based on the given prompt.
     *
     * @param prompt The prompt or request for generating the suggested reply.
     */
    public void execute(String prompt) {
        SuggestReplyInputData suggestReplyInputData = new SuggestReplyInputData(prompt);
        replySuggesterUseCaseInteractor.execute(suggestReplyInputData);
    }
}
