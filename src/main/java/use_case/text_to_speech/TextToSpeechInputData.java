package use_case.text_to_speech;

/**
 * Data class representing input data for the text-to-speech use case.
 */
public class TextToSpeechInputData {
    private final String message;
    /**
     * Constructs a new TextToSpeechInputData.
     */
    public TextToSpeechInputData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
