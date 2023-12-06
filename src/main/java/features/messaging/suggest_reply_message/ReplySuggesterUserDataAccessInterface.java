package features.messaging.suggest_reply_message;

/**
 * The interface for the data access component responsible for reply suggestion generation operations.
 */
public interface ReplySuggesterUserDataAccessInterface {
    String generateSuggestedReply(String prompt);
}
