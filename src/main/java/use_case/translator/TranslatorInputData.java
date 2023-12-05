package use_case.translator;

public class TranslatorInputData {
    final private String message;
    final private String targetLanguage;
    public TranslatorInputData(String message, String targetLanguage) {
        this.message = message;
        this.targetLanguage = targetLanguage;
    }

    public String getMessage() {
        return message;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }
}
