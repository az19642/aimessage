package interface_adapter.text_to_speech;

public class TextToSpeechState {
    private String textToSpeechError = null;

    public TextToSpeechState(TextToSpeechState copy) {
        textToSpeechError = copy.textToSpeechError;
    }

    public TextToSpeechState() {}

    public String getTextToSpeechError() {
        return textToSpeechError;
    }

    public void setTextToSpeechError(String textToSpeechError) {
        this.textToSpeechError = textToSpeechError;
    }

    @Override
    public String toString() {
        return "textToSpeechState{" +
                "textToSpeechError='" + textToSpeechError + '\'' +
                '}';
    }
}
