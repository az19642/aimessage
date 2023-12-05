package use_case.translator;

public interface TranslatorInputBoundary {
    void execute(String textToTranslate, String targetLanguage);
}
