package interface_adapter.suggested_reply_generator;

import use_case.suggested_reply_generator.ReplySuggesterInputBoundary;
import use_case.suggested_reply_generator.ReplySuggesterInputData;

/**
 * The controller class responsible for executing the Suggested Reply Generator use case.
 */
public class ReplySuggesterController {
    private final ReplySuggesterInputBoundary replySuggesterUseCaseInteractor;

    /**
     * Constructs a new ReplySuggesterController with the provided ReplySuggesterInputBoundary.
     *
     * @param replySuggesterUseCaseInteractor The interactor responsible for generating suggested replies.
     */
    public ReplySuggesterController(ReplySuggesterInputBoundary replySuggesterUseCaseInteractor) {
        this.replySuggesterUseCaseInteractor = replySuggesterUseCaseInteractor;
    }

    /**
     * Executes the Suggested Reply Generator use case to generate a suggested reply based on the given prompt.
     *
     * @param prompt The prompt or request for generating the suggested reply.
     */
    public void execute(String prompt) {
        ReplySuggesterInputData replySuggesterInputData = new ReplySuggesterInputData(prompt);
        replySuggesterUseCaseInteractor.execute(replySuggesterInputData);
    }
}
