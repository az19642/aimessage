package interface_adapter.text_to_speech;

import use_case.text_to_speech.TextToSpeechInputBoundary;

public class TextToSpeechController {
    private final TextToSpeechInputBoundary textoSpeechUseCaseInteractor;

    public TextToSpeechController(TextToSpeechInputBoundary textoSpeechUseCaseInteractor) {
        this.textoSpeechUseCaseInteractor = textoSpeechUseCaseInteractor;
    }

    public void execute(String message) {
        textoSpeechUseCaseInteractor.execute(message);
    }
}
