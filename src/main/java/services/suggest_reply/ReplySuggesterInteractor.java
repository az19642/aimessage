package services.suggest_reply;

/**
 * The interactor class for the Suggested Reply Generator use case.
 */
public class ReplySuggesterInteractor implements ReplySuggesterInputBoundary {
    final ReplySuggesterUserDataAccessInterface replySuggesterDataAccessObject;
    final ReplySuggesterOutputBoundary replySuggesterPresenter;

    /**
     * Constructs a new ReplySuggesterInteractor with the provided data access object and presenter.
     *
     * @param replySuggesterDataAccessObject The data access object for suggested reply generation operations.
     * @param replySuggesterPresenter        The presenter for handling output related to the suggested reply generation use case.
     */
    public ReplySuggesterInteractor(ReplySuggesterUserDataAccessInterface replySuggesterDataAccessObject, ReplySuggesterOutputBoundary replySuggesterPresenter) {
        this.replySuggesterDataAccessObject = replySuggesterDataAccessObject;
        this.replySuggesterPresenter = replySuggesterPresenter;
    }

    /**
     * Executes the Suggested Reply Generator use case based on the provided prompt.
     * Prepares the view for a successful response and delegates suggested reply generation to the data access object.
     *
     * @param replySuggesterInputData The prompt or request for generating the suggested reply.
     */
    public void execute(ReplySuggesterInputData replySuggesterInputData) {
        String generatedReply = replySuggesterDataAccessObject.generateSuggestedReply(replySuggesterInputData.getPrompt());

        ReplySuggesterOutputData replySuggesterOutputData = new ReplySuggesterOutputData(generatedReply, false);
        replySuggesterPresenter.prepareSuccessView(replySuggesterOutputData);
    }
}
