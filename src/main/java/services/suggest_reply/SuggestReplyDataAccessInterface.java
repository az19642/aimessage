package services.suggest_reply;

/**
 * The interface for the data access component responsible for reply suggestion generation operations.
 */
public interface SuggestReplyDataAccessInterface {
    String generateSuggestedReply(String prompt);
}
