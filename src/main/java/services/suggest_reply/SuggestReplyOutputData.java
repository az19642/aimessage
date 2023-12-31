package services.suggest_reply;

/**
 * A simple data class representing the output data for the Reply Suggester Generator use case.
 */
public class SuggestReplyOutputData {
    private final String generatedReply;
    private final boolean useCaseFailed;

    /**
     * Constructs a new ReplySuggesterOutputData object.
     *
     * @param useCaseFailed A boolean indicating whether the Reply Suggester Generator use case has failed.
     */
    public SuggestReplyOutputData(String generatedReply, boolean useCaseFailed) {
        this.generatedReply = generatedReply;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Gets the suggested reply.
     *
     * @return The suggested reply.
     */
    public String getGeneratedReply() {
        return generatedReply;
    }
}
