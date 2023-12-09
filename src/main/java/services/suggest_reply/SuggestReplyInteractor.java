package services.suggest_reply;

/**
 * The interactor class for the Suggested Reply Generator use case.
 */
public class SuggestReplyInteractor implements SuggestReplyInputBoundary {
    final SuggestReplyDataAccessInterface replySuggesterDataAccessObject;
    final SuggestReplyOutputBoundary replySuggesterPresenter;

    /**
     * Constructs a new ReplySuggesterInteractor with the provided data access object and presenter.
     *
     * @param replySuggesterDataAccessObject The data access object for suggested reply generation operations.
     * @param replySuggesterPresenter        The presenter for handling output related to the suggested reply generation use case.
     */
    public SuggestReplyInteractor(SuggestReplyDataAccessInterface replySuggesterDataAccessObject,
                                  SuggestReplyOutputBoundary replySuggesterPresenter) {
        this.replySuggesterDataAccessObject = replySuggesterDataAccessObject;
        this.replySuggesterPresenter = replySuggesterPresenter;
    }

    /**
     * Executes the Suggested Reply Generator use case based on the provided prompt.
     * Prepares the view for a successful response and delegates suggested reply generation to the data access object.
     *
     * @param suggestReplyInputData The prompt or request for generating the suggested reply.
     */
    public void execute(SuggestReplyInputData suggestReplyInputData) {
        String generatedReply = replySuggesterDataAccessObject.generateSuggestedReply(
                suggestReplyInputData.getPrompt());

        SuggestReplyOutputData suggestReplyOutputData = new SuggestReplyOutputData(generatedReply, false);
        replySuggesterPresenter.prepareSuccessView(suggestReplyOutputData);
    }
}
