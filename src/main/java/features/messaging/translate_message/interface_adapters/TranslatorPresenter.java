package features.messaging.translate_message.interface_adapters;

import features.messaging.translate_message.TranslatorOutputBoundary;
import features.messaging.translate_message.TranslatorOutputData;
import interface_adapter.ViewManagerModel;

/**
 * Presenter class responsible for handling the output of the translator use case and updating the associated view model.
 */
public class TranslatorPresenter implements TranslatorOutputBoundary {
    private final TranslatorViewModel translatorViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a new TranslatorPresenter with the specified view manager model and translator view model.
     *
     * @param viewManagerModel      The view manager model to be used for managing views.
     * @param translatorViewModel   The translator view model to be updated with translation results.
     */
    public TranslatorPresenter(ViewManagerModel viewManagerModel,
                               TranslatorViewModel translatorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.translatorViewModel = translatorViewModel;
    }

    /**
     * Updates the translator view model with the translated message from the response and sets the active view in the view manager model.
     *
     * @param response The output data containing the translated message.
     */
    @Override
    public void prepareSuccessView(TranslatorOutputData response) {
        TranslatorState translatorState = translatorViewModel.getState();
        translatorState.setTranslatedMessage(response.getTranslatedMessage());
        this.translatorViewModel.setState(translatorState);
        this.translatorViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(translatorViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Updates the translator view model with an error message and notifies the view manager model of the change.
     *
     * @param error The error message indicating a failure in translation.
     */
    @Override
    public void prepareFailView(String error) {
        TranslatorState translatorState = translatorViewModel.getState();
        translatorState.setTranslatorError(error);
        viewManagerModel.firePropertyChanged();
    }
}
