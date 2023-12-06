package services.messaging.text_to_speech;

/**
 * Interface for text-to-speech data access operations.
 * Implementations of this interface are responsible for generating audio from text messages.
 */
public interface TextToSpeechDataAccessInterface {
    boolean generateAudio(String message);
}
