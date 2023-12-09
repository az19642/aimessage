package services.suggest_reply;

/**
 * A simple data class representing input data for the Suggested Reply Generator use case.
 * Currently, this class is empty because no input data is needed to generate a suggested reply with the API.
 */
public class SuggestReplyInputData {
    private final String prompt;

    /**
     * Constructs a new ReplySuggesterInputData instance.
     * Not used because suggest reply generation requires no input data from the user.
     */
    public SuggestReplyInputData(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

}
