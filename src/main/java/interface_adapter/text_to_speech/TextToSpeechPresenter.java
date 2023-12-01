package interface_adapter.text_to_speech;

import interface_adapter.ViewManagerModel;
import use_case.text_to_speech.TextToSpeechOutputBoundary;
import use_case.text_to_speech.TextToSpeechOutputData;

/**
 * Presenter class for handling text-to-speech output and updating the view.
 * This class implements the TextToSpeechOutputBoundary interface.
 */
public class TextToSpeechPresenter implements TextToSpeechOutputBoundary {
    private final TextToSpeechViewModel textToSpeechViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a new TextToSpeechPresenter.
     *
     * @param viewManagerModel         The view manager model to interact with the application's views.
     * @param textToSpeechViewModel    The view model for text-to-speech functionality.
     */
    public TextToSpeechPresenter(ViewManagerModel viewManagerModel,
                                 TextToSpeechViewModel textToSpeechViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.textToSpeechViewModel = textToSpeechViewModel;
    }

    /**
     * Prepares the success view based on the text-to-speech output data.
     *
     * @param response The text-to-speech output data containing the result of a successful operation.
     */
    @Override
    public void prepareSuccessView(TextToSpeechOutputData response) {
        TextToSpeechState textToSpeechState = textToSpeechViewModel.getState();
        this.textToSpeechViewModel.setState(textToSpeechState);
        this.textToSpeechViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(textToSpeechViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view based on the provided error message.
     *
     * @param error The error message indicating the failure in text-to-speech functionality.
     */
    @Override
    public void prepareFailView(String error) {
        TextToSpeechState textToSpeechState = textToSpeechViewModel.getState();
        textToSpeechState.setTextToSpeechError(error);
        viewManagerModel.firePropertyChanged();
    }
}
