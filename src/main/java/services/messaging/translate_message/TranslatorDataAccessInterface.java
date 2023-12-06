package services.messaging.translate_message;

/**
 * Interface defining the contract for accessing translation data in the application.
 */
public interface TranslatorDataAccessInterface {
    String translate(String message, String targetLanguage);
}
