package interface_adapter.suggested_reply_generator;

import use_case.suggested_reply_generator.ReplySuggesterInputBoundary;

public class ReplySuggesterController {
    private final ReplySuggesterInputBoundary replySuggesterUseCaseInteractor;

    public ReplySuggesterController(ReplySuggesterInputBoundary replySuggesterUseCaseInteractor) {
        this.replySuggesterUseCaseInteractor = replySuggesterUseCaseInteractor;
    }

    public void execute(String prompt) { replySuggesterUseCaseInteractor.execute(prompt); }
}
