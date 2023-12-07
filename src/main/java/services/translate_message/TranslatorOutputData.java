package services.translate_message;

/**
 * Represents the output data of the translator use case.
 */
public class TranslatorOutputData {
    private final String translatedMessage;
    private final boolean useCaseFailed;

    /**
     * Constructs a new TranslatorOutputData with the specified translated message and use case failure status.
     *
     * @param translatedMessage The translated message resulting from the translation operation.
     * @param useCaseFailed     A boolean indicating whether the translation use case has failed.
     */
    public TranslatorOutputData(String translatedMessage, boolean useCaseFailed) {
        this.translatedMessage = translatedMessage;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Gets the translated message from the translator output data.
     *
     * @return The translated message.
     */
    public String getTranslatedMessage() { return translatedMessage; }
}
