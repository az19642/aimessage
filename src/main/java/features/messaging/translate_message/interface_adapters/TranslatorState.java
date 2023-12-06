package features.messaging.translate_message.interface_adapters;

/**
 * Represents the state of the translator, including the translated message and any associated error.
 */
public class TranslatorState {
    private String translatedMessage = "";
    private String translatorError = null;

    /**
     * Constructs a new TranslatorState by copying the values from another TranslatorState.
     *
     * @param copy The TranslatorState to copy values from.
     */
    public TranslatorState(TranslatorState copy) {
        translatedMessage = copy.translatedMessage;
        translatorError = copy.translatorError;
    }

    /**
     * Constructs a new empty TranslatorState.
     */
    public TranslatorState() {}

    /**
     * Gets the translated message in the translator state.
     *
     * @return The translated message.
     */
    public String getTranslatedMessage() {
        return translatedMessage;
    }

    /**
     * Sets the translated message in the translator state.
     *
     * @param translatedMessage The translated message to set.
     */
    public void setTranslatedMessage(String translatedMessage) {
        this.translatedMessage = translatedMessage;
    }

    /**
     * Gets the translator error message in the translator state.
     *
     * @return The translator error message.
     */
    public String getTranslatorError() {
        return translatorError;
    }

    /**
     * Sets the translator error message in the translator state.
     *
     * @param translatorError The translator error message to set.
     */
    public void setTranslatorError(String translatorError) {
        this.translatorError = translatorError;
    }

    /**
     * Returns a string representation of the TranslatorState.
     *
     * @return A string representation of the TranslatorState.
     */
    @Override
    public String toString() {
        return "textToSpeechState{" +
                "textToSpeechError='" + translatorError + '\'' +
                '}';
    }
}
