package services.text_to_speech;

/**
 * Data class representing the output of a text-to-speech operation.
 * This class contains information about whether the audio was played successfully
 * and whether the text-to-speech use case encountered a failure.
 */
public class TextToSpeechOutputData {
    private final boolean audioPlayed;
    private final boolean useCaseFailed;

    /**
     * Constructs a new TextToSpeechOutputData with the specified parameters.
     *
     * @param audioPlayed   True if the audio was played successfully, false otherwise.
     * @param useCaseFailed True if the text-to-speech use case encountered a failure, false otherwise.
     */
    public TextToSpeechOutputData(boolean audioPlayed, boolean useCaseFailed) {
        this.audioPlayed = audioPlayed;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Gets the result of the text-to-speech operation, indicating whether the audio was played successfully.
     *
     * @return True if the audio was played successfully, false otherwise.
     */
    public boolean getSpeechResult() {
        return audioPlayed;
    }
}
