package features.messaging.translate_message;

/**
 * Interface defining the contract for the output boundary of the translator use case.
 */
public interface TranslatorOutputBoundary {
    void prepareSuccessView(TranslatorOutputData response);

    void prepareFailView(String error);
}
