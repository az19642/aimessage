package use_case.suggested_reply_generator;

public class ReplySuggesterInteractor {
    final ReplySuggesterUserDataAccessInterface replySuggesterDataAccessObject;
    final ReplySuggesterOutputBoundary replySuggesterPresenter;

    public ReplySuggesterInteractor(ReplySuggesterUserDataAccessInterface replySuggesterDataAccessObject, ReplySuggesterOutputBoundary replySuggesterPresenter) {
        this.replySuggesterDataAccessObject = replySuggesterDataAccessObject;
        this.replySuggesterPresenter = replySuggesterPresenter;
    }

    public void execute(String prompt) {
        String generatedReply = replySuggesterDataAccessObject.generateSuggestedReply(prompt);

        ReplySuggesterOutputData replySuggesterOutputData = new ReplySuggesterOutputData(generatedReply, false);
        replySuggesterPresenter.prepareSuccessView(replySuggesterOutputData);
    }
}
