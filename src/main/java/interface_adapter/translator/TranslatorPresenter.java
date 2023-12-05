package interface_adapter.translator;

import interface_adapter.ViewManagerModel;
import use_case.translator.TranslatorOutputBoundary;
import use_case.translator.TranslatorOutputData;

public class TranslatorPresenter implements TranslatorOutputBoundary {
    private final TranslatorViewModel translatorViewModel;
    private ViewManagerModel viewManagerModel;

    public TranslatorPresenter(ViewManagerModel viewManagerModel,
                               TranslatorViewModel translatorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.translatorViewModel = translatorViewModel;
    }

    @Override
    public void prepareSuccessView(TranslatorOutputData response) {
        TranslatorState translatorState = translatorViewModel.getState();
        translatorState.setTranslatedMessage(response.getTranslatedMessage());
        this.translatorViewModel.setState(translatorState);
        this.translatorViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(translatorViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        TranslatorState translatorState = translatorViewModel.getState();
        translatorState.setTranslatorError(error);
        viewManagerModel.firePropertyChanged();
    }
}
