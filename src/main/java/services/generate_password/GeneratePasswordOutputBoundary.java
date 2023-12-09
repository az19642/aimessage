package services.generate_password;

/**
 * The output boundary interface for the Password Generator use case.
 * Defines methods for preparing views in response to successful or failed password generation.
 */
public interface GeneratePasswordOutputBoundary {
    void prepareSuccessView(GeneratePasswordOutputData response);

    void prepareFailView(String error);
}
