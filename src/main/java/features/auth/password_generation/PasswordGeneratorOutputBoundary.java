package features.auth.password_generation;

/**
 * The output boundary interface for the Password Generator use case.
 * Defines methods for preparing views in response to successful or failed password generation.
 */
public interface PasswordGeneratorOutputBoundary {
    void prepareSuccessView(PasswordGeneratorOutputData response);

    void prepareFailView(String error);
}
