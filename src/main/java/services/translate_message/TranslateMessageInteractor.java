package services.translate_message;

/**
 * Interactor class for the translator use case, responsible for coordinating translation operations.
 */
public class TranslateMessageInteractor implements TranslateMessageInputBoundary {
    final TranslateMessageDataAccessInterface translatorDataAccessObject;
    final TranslateMessageOutputBoundary translatorPresenter;

    /**
     * Constructs a new TranslatorInteractor with the specified data access object and presenter.
     *
     * @param translatorDataAccessObject The data access object responsible for translation.
     * @param translatorPresenter        The presenter to handle the output of the translation operation.
     */
    public TranslateMessageInteractor(TranslateMessageDataAccessInterface translatorDataAccessObject,
                                      TranslateMessageOutputBoundary translatorPresenter) {
        this.translatorDataAccessObject = translatorDataAccessObject;
        this.translatorPresenter = translatorPresenter;
    }

    /**
     * Executes the translator use case with the provided input data.
     *
     * @param translateMessageInputData The input data for the translation operation.
     */
    @Override
    public void execute(TranslateMessageInputData translateMessageInputData) {
        String translatedMessage = translatorDataAccessObject.translate(translateMessageInputData.getMessage(),
                translateMessageInputData.getTargetLanguage());

        TranslateMessageOutputData translateMessageOutputData = new TranslateMessageOutputData(translatedMessage,
                false);
        // Notify the presenter to prepare the view based on the output data
        translatorPresenter.prepareSuccessView(translateMessageOutputData);
    }
}

