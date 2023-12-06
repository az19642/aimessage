package services.messaging.text_to_speech;

/**
 * Interface for the input boundary of the text-to-speech use case.
 * Implementations of this interface are responsible for executing the text-to-speech operation.
 */
public interface TextToSpeechInputBoundary {
    void execute(TextToSpeechInputData textToSpeechInputData);
}
