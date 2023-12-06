package features.messaging.text_to_speech;

/**
 * Interface for the output boundary of the text-to-speech use case.
 * Implementations of this interface are responsible for handling and presenting
 * the results of text-to-speech operations.
 */
public interface TextToSpeechOutputBoundary {
    void prepareSuccessView(TextToSpeechOutputData response);

    void prepareFailView(String error);
}
