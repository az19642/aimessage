package services.translate_message.interface_adapters;

import services.translate_message.TranslatorInputBoundary;
import services.translate_message.TranslatorInputData;

/**
 * Controller class responsible for handling translation requests and interacting with the translator use case.
 */
public class MessageTranslatorController {
    private final TranslatorInputBoundary translatorUseCaseInteractor;

    /**
     * Constructs a new TranslatorController with the specified translator use case interactor.
     *
     * @param translatorUseCaseInteractor The translator use case interactor to be used for handling translation requests.
     */
    public MessageTranslatorController(TranslatorInputBoundary translatorUseCaseInteractor) {
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
