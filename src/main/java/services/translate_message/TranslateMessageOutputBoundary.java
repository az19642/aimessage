package services.translate_message;

/**
 * Interface defining the contract for the output boundary of the translator use case.
 */
public interface TranslateMessageOutputBoundary {
    void prepareSuccessView(TranslateMessageOutputData response);

    void prepareFailView(String error);
}
