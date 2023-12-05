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
    public void execute(String textToTranslate, String targetLanguage) {
        String translatedMessage = translatorDataAccessObject.translate(textToTranslate, targetLanguage);

        TranslatorOutputData translatorOutputData =
                new TranslatorOutputData(translatedMessage, false);
        // Notify the presenter to prepare the view based on the output data
        translatorPresenter.prepareSuccessView(translatorOutputData);
    }
}

