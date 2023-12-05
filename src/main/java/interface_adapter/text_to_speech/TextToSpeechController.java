package interface_adapter.text_to_speech;

import use_case.text_to_speech.TextToSpeechInputBoundary;

/**
 * Controller class for text-to-speech functionality.
 * This class serves as an intermediary between the application interface
 * and the text-to-speech use case.
 */
public class TextToSpeechController {
    private final TextToSpeechInputBoundary textoSpeechUseCaseInteractor;

    /**
     * Constructs a new TextToSpeechController.
     *
     * @param textoSpeechUseCaseInteractor The text-to-speech use case interactor to be used.
     */
    public TextToSpeechController(TextToSpeechInputBoundary textoSpeechUseCaseInteractor) {
        this.textoSpeechUseCaseInteractor = textoSpeechUseCaseInteractor;
    }

    /**
     * Executes the text-to-speech functionality with the given message.
     *
     * @param message The text message to be converted to speech.
     */
    public void execute(String message) {
        textoSpeechUseCaseInteractor.execute(message);
    }
}
