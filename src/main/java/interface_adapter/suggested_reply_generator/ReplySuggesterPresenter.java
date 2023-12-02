package interface_adapter.suggested_reply_generator;

import interface_adapter.ViewManagerModel;
import use_case.suggested_reply_generator.ReplySuggesterOutputBoundary;
import use_case.suggested_reply_generator.ReplySuggesterOutputData;

public class ReplySuggesterPresenter implements ReplySuggesterOutputBoundary {

    private final ReplySuggesterViewModel replySuggesterViewModel;
    private ViewManagerModel viewManagerModel;

    public ReplySuggesterPresenter(ReplySuggesterViewModel replySuggesterViewModel, ViewManagerModel viewManagerModel) {
        this.replySuggesterViewModel = replySuggesterViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * @param response
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
     * @param error
     */
    @Override
    public void prepareFailView(String error) {
        ReplySuggesterState replySuggesterState = replySuggesterViewModel.getState();
        replySuggesterState.setReplySuggesterError(error);
        viewManagerModel.firePropertyChanged();
    }
}
