package use_case.translator;

public class TranslatorInteractor implements TranslatorInputBoundary {
    final TranslatorDataAccessInterface translatorDataAccessObject;
    final TranslatorOutputBoundary translatorPresenter;

    public TranslatorInteractor(TranslatorDataAccessInterface translatorDataAccessObject,
                                TranslatorOutputBoundary translatorPresenter) {
        this.translatorDataAccessObject = translatorDataAccessObject;
        this.translatorPresenter = translatorPresenter;
    }

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

