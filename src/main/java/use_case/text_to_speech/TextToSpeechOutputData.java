package use_case.text_to_speech;

public class TextToSpeechOutputData {
    private final boolean audioPlayed;
    private final boolean useCaseFailed;

    public TextToSpeechOutputData(boolean audioPlayed, boolean useCaseFailed) {
        this.audioPlayed = audioPlayed;
        this.useCaseFailed = useCaseFailed;
    }

    public boolean getSpeechResult() { return audioPlayed; }
}
