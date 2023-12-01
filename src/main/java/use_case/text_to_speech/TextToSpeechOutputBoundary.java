package use_case.text_to_speech;

public interface TextToSpeechOutputBoundary {
    void prepareSuccessView(TextToSpeechOutputData response);

    void prepareFailView(String error);
}
