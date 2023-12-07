package services.translate_message;

/**
 * Interactor class for the translator use case, responsible for coordinating translation operations.
 */
public class TranslatorInteractor implements TranslatorInputBoundary {
    final TranslatorDataAccessInterface translatorDataAccessObject;
    final TranslatorOutputBoundary translatorPresenter;

    /**
     * Constructs a new TranslatorInteractor with the specified data access object and presenter.
     *
     * @param translatorDataAccessObject The data access object responsible for translation.
     * @param translatorPresenter        The presenter to handle the output of the translation operation.
     */
    public TranslatorInteractor(TranslatorDataAccessInterface translatorDataAccessObject,
                                TranslatorOutputBoundary translatorPresenter) {
        this.translatorDataAccessObject = translatorDataAccessObject;
        this.translatorPresenter = translatorPresenter;
    }

    /**
     * Executes the translator use case with the provided input data.
     *
     * @param translatorInputData The input data for the translation operation.
     */
    @Override
    public void execute(TranslatorInputData translatorInputData) {
        String translatedMessage = translatorDataAccessObject.translate(translatorInputData.getMessage(),
                translatorInputData.getTargetLanguage());

        TranslatorOutputData translatorOutputData =
                new TranslatorOutputData(translatedMessage, false);
        // Notify the presenter to prepare the view based on the output data
        translatorPresenter.prepareSuccessView(translatorOutputData);
    }
}

