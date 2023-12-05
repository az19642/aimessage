package interface_adapter.translator;

import use_case.translator.TranslatorInputBoundary;
import use_case.translator.TranslatorInputData;

public class TranslatorController {
    private final TranslatorInputBoundary translatorUseCaseInteractor;

    public TranslatorController(TranslatorInputBoundary translatorUseCaseInteractor) {
        this.translatorUseCaseInteractor = translatorUseCaseInteractor;
    }

    public void execute(String textToTranslate, String targetLanguage) {
        TranslatorInputData translatorInputData = new TranslatorInputData(textToTranslate, targetLanguage);
        translatorUseCaseInteractor.execute(translatorInputData);
    }
}
