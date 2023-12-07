package services.translate_message.interface_adapters;

import services.translate_message.TranslatorOutputBoundary;
import services.translate_message.TranslatorOutputData;
import interface_adapter.ViewManagerModel;

/**
 * Presenter class responsible for handling the output of the translator use case and updating the associated view model.
 */
public class MessageTranslatorPresenter implements TranslatorOutputBoundary {
    private final MessageTranslatorViewModel messageTranslatorViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a new TranslatorPresenter with the specified view manager model and translator view model.
     *
     * @param viewManagerModel      The view manager model to be used for managing views.
     * @param messageTranslatorViewModel   The translator view model to be updated with translation results.
     */
    public MessageTranslatorPresenter(ViewManagerModel viewManagerModel,
                                      MessageTranslatorViewModel messageTranslatorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.messageTranslatorViewModel = messageTranslatorViewModel;
    }

    /**
     * Updates the translator view model with the translated message from the response and sets the active view in the view manager model.
     *
     * @param response The output data containing the translated message.
     */
    @Override
    public void prepareSuccessView(TranslatorOutputData response) {
        MessageTranslatorState messageTranslatorState = messageTranslatorViewModel.getState();
        messageTranslatorState.setTranslatedMessage(response.getTranslatedMessage());
        this.messageTranslatorViewModel.setState(messageTranslatorState);
        this.messageTranslatorViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(messageTranslatorViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Updates the translator view model with an error message and notifies the view manager model of the change.
     *
     * @param error The error message indicating a failure in translation.
     */
    @Override
    public void prepareFailView(String error) {
        MessageTranslatorState messageTranslatorState = messageTranslatorViewModel.getState();
        messageTranslatorState.setTranslatorError(error);
        viewManagerModel.firePropertyChanged();
    }
}
