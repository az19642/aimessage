package interface_adapter.translator;

import use_case.translator.TranslatorInputBoundary;

public class TranslatorController {
    private final TranslatorInputBoundary translatorUseCaseInteractor;

    public TranslatorController(TranslatorInputBoundary translatorUseCaseInteractor) {
        this.translatorUseCaseInteractor = translatorUseCaseInteractor;
    }

    public void execute(String textToTranslate, String targetLanguage) {
        translatorUseCaseInteractor.execute(textToTranslate, targetLanguage);
    }
}
