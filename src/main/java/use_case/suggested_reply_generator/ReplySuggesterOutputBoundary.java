package use_case.suggested_reply_generator;

public interface ReplySuggesterOutputBoundary {
    void prepareSuccessView(ReplySuggesterOutputData response);

    void prepareFailView(String error);
}
