package use_case.suggested_reply_generator;

/**
 * The input boundary interface for the Suggested Reply Generator use case.
 * Defines the method signature for executing the Suggested Reply Generator based on a given prompt.
 */

public interface ReplySuggesterInputBoundary {
    void execute(String prompt);
}
