package use_case.text_to_speech;

/**
 * Interactor class for the text-to-speech use case.
 * Implements the TextToSpeechInputBoundary interface to handle text-to-speech operations.
 */
public class TextToSpeechInteractor implements TextToSpeechInputBoundary {
    final TextToSpeechDataAccessInterface textToSpeechDataAccessObject;
    final TextToSpeechOutputBoundary textToSpeechPresenter;

    /**
     * Constructs a new TextToSpeechInteractor.
     *
     * @param textToSpeechDataAccessObject The data access object for text-to-speech operations.
     * @param textToSpeechPresenter        The presenter for handling text-to-speech output.
     */
    public TextToSpeechInteractor(TextToSpeechDataAccessInterface textToSpeechDataAccessObject,
                                  TextToSpeechOutputBoundary textToSpeechPresenter) {
        this.textToSpeechDataAccessObject = textToSpeechDataAccessObject;
        this.textToSpeechPresenter = textToSpeechPresenter;
    }

    /**
     * Executes the text-to-speech operation with the given message.
     *
     * @param textToSpeechInputData The text message to be converted into speech.
     */
    @Override
    public void execute(TextToSpeechInputData textToSpeechInputData) {
        boolean messagePlayed = textToSpeechDataAccessObject.generateAudio(textToSpeechInputData.getMessage());

        TextToSpeechOutputData textToSpeechOutputData =
                new TextToSpeechOutputData(messagePlayed, false);
        // Notify the presenter to prepare the view based on the output data
        textToSpeechPresenter.prepareSuccessView(textToSpeechOutputData);
    }
}
