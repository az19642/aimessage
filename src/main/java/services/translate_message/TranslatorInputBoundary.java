package services.translate_message;

/**
 * Interface defining the contract for the input boundary of the translator use case.
 */
public interface TranslatorInputBoundary {
    void execute(TranslatorInputData translatorInputData);
}
