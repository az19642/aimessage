package features.messaging.translate_message.interface_adapters;

import features.messaging.translate_message.TranslatorInputBoundary;
import features.messaging.translate_message.TranslatorInputData;

/**
 * Controller class responsible for handling translation requests and interacting with the translator use case.
 */
public class TranslatorController {
    private final TranslatorInputBoundary translatorUseCaseInteractor;

    /**
     * Constructs a new TranslatorController with the specified translator use case interactor.
     *
     * @param translatorUseCaseInteractor The translator use case interactor to be used for handling translation requests.
     */
    public TranslatorController(TranslatorInputBoundary translatorUseCaseInteractor) {
        this.translatorUseCaseInteractor = translatorUseCaseInteractor;
    }

    /**
     * Executes a translation request by creating a TranslatorInputData object and invoking the translator use case interactor.
     *
     * @param textToTranslate The text to be translated.
     * @param targetLanguage  The target language into which the text should be translated.
     */
    public void execute(String textToTranslate, String targetLanguage) {
        TranslatorInputData translatorInputData = new TranslatorInputData(textToTranslate, targetLanguage);
        translatorUseCaseInteractor.execute(translatorInputData);
    }
}
