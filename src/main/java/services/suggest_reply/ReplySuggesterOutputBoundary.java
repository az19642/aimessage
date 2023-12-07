package services.suggest_reply;

/**
 * The output boundary interface for the Suggested Reply Generator use case.
 * Defines methods for preparing views in response to successful or failed suggested reply generation.
 */
public interface ReplySuggesterOutputBoundary {
    void prepareSuccessView(ReplySuggesterOutputData response);

    void prepareFailView(String error);
}
