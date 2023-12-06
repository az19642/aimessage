package features.messaging.suggest_reply_message.interface_adapters;

import features.messaging.suggest_reply_message.ReplySuggesterOutputBoundary;
import interface_adapter.ViewManagerModel;
import features.messaging.suggest_reply_message.ReplySuggesterOutputData;

/**
 * The presenter class responsible for handling the presentation logic related to the Suggested Reply Generator use case.
 */
public class ReplySuggesterPresenter implements ReplySuggesterOutputBoundary {

    private final ReplySuggesterViewModel replySuggesterViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a new ReplySuggesterPresenter with the provided ViewManagerModel and ReplySuggesterViewModel.
     *
     * @param viewManagerModel        The model responsible for managing views in the application.
     * @param replySuggesterViewModel The view model for the Suggested Reply Generator interface.
     */
    public ReplySuggesterPresenter(ReplySuggesterViewModel replySuggesterViewModel, ViewManagerModel viewManagerModel) {
        this.replySuggesterViewModel = replySuggesterViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the view for a successful response from the Suggested Reply Generator use case.
     * Updates the view model and view manager to reflect the success state.
     *
     * @param response The output data from the Suggested Reply Generator use case.
     */
    @Override
    public void prepareSuccessView(ReplySuggesterOutputData response) {
        ReplySuggesterState replySuggesterState = replySuggesterViewModel.getState();
        replySuggesterState.setGeneratedReply(response.getGeneratedReply());
        this.replySuggesterViewModel.setState(replySuggesterState);
        this.replySuggesterViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(replySuggesterViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view for a failed response from the Suggested Reply Generator use case.
     *
     * @param error The error message associated with the failed operation.
     */
    @Override
    public void prepareFailView(String error) {
        ReplySuggesterState replySuggesterState = replySuggesterViewModel.getState();
        replySuggesterState.setReplySuggesterError(error);
        viewManagerModel.firePropertyChanged();
    }
}
