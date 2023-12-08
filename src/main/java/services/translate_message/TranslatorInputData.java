package services.translate_message;

/**
 * Represents the input data for the translator use case.
 */
public class TranslatorInputData {
    final private String message;

    /**
     * Constructs a new TranslatorInputData with the specified message and target language.
     *
     * @param message The message to be translated.
     */
    public TranslatorInputData(String message) {
        this.message = message;
    }

    /**
     * Gets the message to be translated.
     *
     * @return The message to be translated.
     */
    public String getMessage() {
        return message;
    }
}
