package services.messaging.text_to_speech.interface_adapters;

/**
 * Represents the state of the text-to-speech functionality.
 * This class encapsulates information about any errors that may occur during text-to-speech processing.
 */
public class TextToSpeechState {
    private String textToSpeechError = null;

    /**
     * Constructs a new TextToSpeechState by copying the content of another instance.
     *
     * @param copy The TextToSpeechState to copy.
     */
    public TextToSpeechState(TextToSpeechState copy) {
        textToSpeechError = copy.textToSpeechError;
    }

    /**
     * Constructs a new TextToSpeechState with default values.
     */
    public TextToSpeechState() {}

    /**
     * Gets the text-to-speech error message.
     *
     * @return The text-to-speech error message, or null if there is no error.
     */
    public String getTextToSpeechError() {
        return textToSpeechError;
    }

    /**
     * Sets the text-to-speech error message.
     *
     * @param textToSpeechError The text-to-speech error message to set.
     */
    public void setTextToSpeechError(String textToSpeechError) {
        this.textToSpeechError = textToSpeechError;
    }

    /**
     * Returns a string representation of the TextToSpeechState.
     *
     * @return A string representation containing the text-to-speech error.
     */
    @Override
    public String toString() {
        return "textToSpeechState{" +
                "textToSpeechError='" + textToSpeechError + '\'' +
                '}';
    }
}
