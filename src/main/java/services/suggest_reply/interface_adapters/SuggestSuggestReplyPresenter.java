package services.suggest_reply.interface_adapters;

import interface_adapters.ViewManagerModel;
import services.suggest_reply.SuggestReplyOutputBoundary;
import services.suggest_reply.SuggestReplyOutputData;

/**
 * The presenter class responsible for handling the presentation logic related to the Suggested Reply Generator use case.
 */
public class SuggestSuggestReplyPresenter implements SuggestReplyOutputBoundary {

    private final SuggestReplyViewModel suggestReplyViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a new ReplySuggesterPresenter with the provided ViewManagerModel and ReplySuggesterViewModel.
     *
     * @param viewManagerModel      The model responsible for managing views in the application.
     * @param suggestReplyViewModel The view model for the Suggested Reply Generator interface.
     */
    public SuggestSuggestReplyPresenter(SuggestReplyViewModel suggestReplyViewModel,
                                        ViewManagerModel viewManagerModel) {
        this.suggestReplyViewModel = suggestReplyViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the view for a successful response from the Suggested Reply Generator use case.
     * Updates the view model and view manager to reflect the success state.
     *
     * @param response The output data from the Suggested Reply Generator use case.
     */
    @Override
    public void prepareSuccessView(SuggestReplyOutputData response) {
        SuggestReplyState suggestReplyState = suggestReplyViewModel.getState();
        suggestReplyState.setGeneratedReply(response.getGeneratedReply());
        this.suggestReplyViewModel.setState(suggestReplyState);
        this.suggestReplyViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(suggestReplyViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view for a failed response from the Suggested Reply Generator use case.
     *
     * @param error The error message associated with the failed operation.
     */
    @Override
    public void prepareFailView(String error) {
        SuggestReplyState suggestReplyState = suggestReplyViewModel.getState();
        suggestReplyState.setReplySuggesterError(error);
        viewManagerModel.firePropertyChanged();
    }
}
