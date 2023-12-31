package services.translate_message;

/**
 * Interface defining the contract for accessing translation data in the application.
 */
public interface TranslateMessageDataAccessInterface {
    String translate(String message, String targetLanguage);
}
