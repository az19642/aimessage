package services.translate_message;

/**
 * Represents the input data for the translator use case.
 */
public class TranslateMessageInputData {
    final private String message;
    final private String targetLanguage;

    /**
     * Constructs a new TranslatorInputData with the specified message and target language.
     *
     * @param message        The message to be translated.
     * @param targetLanguage The target language into which the message should be translated.
     */
    public TranslateMessageInputData(String message, String targetLanguage) {
        this.message = message;
        this.targetLanguage = targetLanguage;
    }

    /**
     * Gets the message to be translated.
     *
     * @return The message to be translated.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the target language into which the message should be translated.
     *
     * @return The target language for translation.
     */
    public String getTargetLanguage() {
        return targetLanguage;
    }
}
