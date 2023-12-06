package services.messaging.text_to_speech.interface_adapters;

import services.messaging.text_to_speech.TextToSpeechInputBoundary;
import services.messaging.text_to_speech.TextToSpeechInputData;

/**
 * Controller class for text-to-speech functionality.
 * This class serves as an intermediary between the application interface
 * and the text-to-speech use case.
 */
public class TextToSpeechController {
    private final TextToSpeechInputBoundary textToSpeechUseCaseInteractor;

    /**
     * Constructs a new TextToSpeechController.
     *
     * @param textoSpeechUseCaseInteractor The text-to-speech use case interactor to be used.
     */
    public TextToSpeechController(TextToSpeechInputBoundary textoSpeechUseCaseInteractor) {
        this.textToSpeechUseCaseInteractor = textoSpeechUseCaseInteractor;
    }

    /**
     * Executes the text-to-speech functionality with the given message.
     *
     * @param message The text message to be converted to speech.
     */
    public void execute(String message) {
        TextToSpeechInputData textToSpeechInputData = new TextToSpeechInputData(message);
        textToSpeechUseCaseInteractor.execute(textToSpeechInputData);
    }
}
