package use_case.suggested_reply_generator;

public class ReplySuggesterOutputData {
    private final String generatedReply;
    private final boolean useCaseFailed;

    public ReplySuggesterOutputData(String generatedReply, boolean useCaseFailed) {
        this.generatedReply = generatedReply;
        this.useCaseFailed = useCaseFailed;
    }

    public String getGeneratedReply() { return generatedReply; }
}
