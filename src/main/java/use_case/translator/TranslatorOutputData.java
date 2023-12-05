package use_case.translator;

public class TranslatorOutputData {
    private final String translatedMessage;
    private final boolean useCaseFailed;

    public TranslatorOutputData(String translatedMessage, boolean useCaseFailed) {
        this.translatedMessage = translatedMessage;
        this.useCaseFailed = useCaseFailed;
    }

    public String getTranslatedMessage() { return translatedMessage; }
}
