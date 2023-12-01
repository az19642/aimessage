package interface_adapter.text_to_speech;

import interface_adapter.ViewManagerModel;
import use_case.text_to_speech.TextToSpeechOutputBoundary;
import use_case.text_to_speech.TextToSpeechOutputData;

public class TextToSpeechPresenter implements TextToSpeechOutputBoundary {
    private final TextToSpeechViewModel textToSpeechViewModel;
    private ViewManagerModel viewManagerModel;

    public TextToSpeechPresenter(ViewManagerModel viewManagerModel,
                                 TextToSpeechViewModel textToSpeechViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.textToSpeechViewModel = textToSpeechViewModel;
    }
    @Override
    public void prepareSuccessView(TextToSpeechOutputData response) {
        TextToSpeechState textToSpeechState = textToSpeechViewModel.getState();
        this.textToSpeechViewModel.setState(textToSpeechState);
        this.textToSpeechViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(textToSpeechViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        TextToSpeechState textToSpeechState = textToSpeechViewModel.getState();
        textToSpeechState.setTextToSpeechError(error);
        viewManagerModel.firePropertyChanged();
    }
}
