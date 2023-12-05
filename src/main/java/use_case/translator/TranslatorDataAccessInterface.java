package use_case.translator;

/**
 * Interface defining the contract for accessing translation data in the application.
 */
public interface TranslatorDataAccessInterface {
    String translate(String message, String targetLanguage);
}
