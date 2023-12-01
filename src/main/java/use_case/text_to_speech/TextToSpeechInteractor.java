package use_case.text_to_speech;

public class TextToSpeechInteractor implements TextToSpeechInputBoundary {
    final TextToSpeechDataAccessInterface textToSpeechDataAccessObject;
    final TextToSpeechOutputBoundary textToSpeechPresenter;

    public TextToSpeechInteractor(TextToSpeechDataAccessInterface textToSpeechDataAccessObject,
                                  TextToSpeechOutputBoundary textToSpeechPresenter) {
        this.textToSpeechDataAccessObject = textToSpeechDataAccessObject;
        this.textToSpeechPresenter = textToSpeechPresenter;
    }

    @Override
    public void execute(String message) {
        boolean messagePlayed = textToSpeechDataAccessObject.generateAudio(message);

        TextToSpeechOutputData textToSpeechOutputData =
                new TextToSpeechOutputData(messagePlayed, false);
        textToSpeechPresenter.prepareSuccessView(textToSpeechOutputData);
    }
}
