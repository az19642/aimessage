package services.translate_message.interface_adapters;

import services.translate_message.TranslateMessageInputBoundary;
import services.translate_message.TranslateMessageInputData;

/**
 * Controller class responsible for handling translation requests and interacting with the translator use case.
 */
public class TranslateMessageController {
    private final TranslateMessageInputBoundary translatorUseCaseInteractor;

    /**
     * Constructs a new TranslatorController with the specified translator use case interactor.
     *
     * @param translatorUseCaseInteractor The translator use case interactor to be used for handling translation requests.
     */
    public TranslateMessageController(TranslateMessageInputBoundary translatorUseCaseInteractor) {
        this.translatorUseCaseInteractor = translatorUseCaseInteractor;
    }

    /**
     * Executes a translation request by creating a TranslatorInputData object and invoking the translator use case interactor.
     *
     * @param textToTranslate The text to be translated.
     * @param targetLanguage  The target language into which the text should be translated.
     */
    public void execute(String textToTranslate, String targetLanguage) {
        TranslateMessageInputData translateMessageInputData = new TranslateMessageInputData(textToTranslate,
                targetLanguage);
        translatorUseCaseInteractor.execute(translateMessageInputData);
    }
}
