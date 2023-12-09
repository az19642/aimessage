package services.translate_message.interface_adapters;

import interface_adapters.ViewManagerModel;
import services.translate_message.TranslateMessageOutputBoundary;
import services.translate_message.TranslateMessageOutputData;

/**
 * Presenter class responsible for handling the output of the translator use case and updating the associated view model.
 */
public class TranslateMessagePresenter implements TranslateMessageOutputBoundary {
    private final TranslateMessageViewModel translateMessageViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a new TranslatorPresenter with the specified view manager model and translator view model.
     *
     * @param viewManagerModel          The view manager model to be used for managing views.
     * @param translateMessageViewModel The translator view model to be updated with translation results.
     */
    public TranslateMessagePresenter(ViewManagerModel viewManagerModel,
                                     TranslateMessageViewModel translateMessageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.translateMessageViewModel = translateMessageViewModel;
    }

    /**
     * Updates the translator view model with the translated message from the response and sets the active view in the view manager model.
     *
     * @param response The output data containing the translated message.
     */
    @Override
    public void prepareSuccessView(TranslateMessageOutputData response) {
        TranslateMessageState translateMessageState = translateMessageViewModel.getState();
        translateMessageState.setTranslatedMessage(response.getTranslatedMessage());
        this.translateMessageViewModel.setState(translateMessageState);
        this.translateMessageViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(translateMessageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Updates the translator view model with an error message and notifies the view manager model of the change.
     *
     * @param error The error message indicating a failure in translation.
     */
    @Override
    public void prepareFailView(String error) {
        TranslateMessageState translateMessageState = translateMessageViewModel.getState();
        translateMessageState.setTranslatorError(error);
        viewManagerModel.firePropertyChanged();
    }
}
