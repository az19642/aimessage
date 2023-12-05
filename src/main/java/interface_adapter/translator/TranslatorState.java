package interface_adapter.translator;

public class TranslatorState {
    private String translatedMessage = "";
    private String translatorError = null;

    public TranslatorState(TranslatorState copy) {
        translatedMessage = copy.translatedMessage;
        translatorError = copy.translatorError;
    }

    public TranslatorState() {}

    public String getTranslatedMessage() {
        return translatedMessage;
    }

    public void setTranslatedMessage(String translatedMessage) {
        this.translatedMessage = translatedMessage;
    }

    public String getTranslatorError() {
        return translatorError;
    }

    public void setTranslatorError(String textToSpeechError) {
        this.translatorError = textToSpeechError;
    }

    @Override
    public String toString() {
        return "textToSpeechState{" +
                "textToSpeechError='" + translatorError + '\'' +
                '}';
    }
}
