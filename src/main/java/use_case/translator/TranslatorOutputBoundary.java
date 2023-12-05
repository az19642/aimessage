package use_case.translator;

public interface TranslatorOutputBoundary {
    void prepareSuccessView(TranslatorOutputData response);

    void prepareFailView(String error);
}
