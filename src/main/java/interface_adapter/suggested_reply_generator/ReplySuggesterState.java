package interface_adapter.suggested_reply_generator;

public class ReplySuggesterState {
    private String generatedReply = "";
    private String replySuggesterError = null;

    public ReplySuggesterState(ReplySuggesterState copy) {
        generatedReply = copy.generatedReply;
        replySuggesterError = copy.replySuggesterError;
    }

    public ReplySuggesterState() {}

    public String getGeneratedReply() {
        return generatedReply;
    }

    public void setGeneratedReply(String generatedReply) {
        this.generatedReply = generatedReply;
    }

    public String ReplySuggesterState() { return replySuggesterError; }

    public void setReplySuggesterError(String replySuggesterError) {
        this.replySuggesterError = replySuggesterError;
    }

    @Override
    public String toString() {
        return "ReplySuggesterState{" +
                "replySuggesterError='" + replySuggesterError + '\'' +
                '}';
    }
}
