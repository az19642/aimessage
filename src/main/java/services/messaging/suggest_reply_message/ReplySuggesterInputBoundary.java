package services.messaging.suggest_reply_message;

/**
 * The input boundary interface for the Suggested Reply Generator use case.
 * Defines the method signature for executing the Suggested Reply Generator based on a given prompt.
 */

public interface ReplySuggesterInputBoundary {
    void execute(ReplySuggesterInputData replySuggesterInputData);
}
