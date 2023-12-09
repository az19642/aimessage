package services.suggest_reply.interface_adapters;

/**
 * The state class that holds information related to the Suggested Reply Generator interface, including error messages.
 */
public class SuggestReplyState {
    private String generatedReply = "";
    private String replySuggesterError = null;

    /**
     * Constructs a new ReplySuggesterState by copying the content from an existing state.
     *
     * @param copy The ReplySuggesterState to copy the content from.
     */
    public SuggestReplyState(SuggestReplyState copy) {
        generatedReply = copy.generatedReply;
        replySuggesterError = copy.replySuggesterError;
    }

    public SuggestReplyState() {
    }

    /**
     * Gets the generated suggested reply from API call.
     *
     * @return The generated suggested reply from API call.
     */
    public String getGeneratedReply() {
        return generatedReply;
    }

    /**
     * Sets the generated suggested reply from API call.
     *
     * @param generatedReply The new generated suggested reply to be set.
     */
    public void setGeneratedReply(String generatedReply) {
        this.generatedReply = generatedReply;
    }

    /**
     * Gets the error message associated with the Suggested Reply Generator.
     *
     * @return The error message as a string.
     */
    public String getReplySuggesterError() {
        return replySuggesterError;
    }

    /**
     * Sets the error message associated with the Suggested Reply Generator.
     *
     * @param replySuggesterError The error message to set.
     */
    public void setReplySuggesterError(String replySuggesterError) {
        this.replySuggesterError = replySuggesterError;
    }

    /**
     * Returns a string representation of the ReplySuggesterState.
     *
     * @return A string representation that includes the error message.
     */
    @Override
    public String toString() {
        return "ReplySuggesterState{" + "replySuggesterError='" + replySuggesterError + '\'' + '}';
    }
}
