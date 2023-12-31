package services.generate_password;

/**
 * The input boundary interface for the Password Generator use case.
 * Defines the method signature for executing the Password Generator based on a given prompt.
 */
public interface GeneratePasswordInputBoundary {
    void execute(GeneratePasswordInputData generatePasswordInputData);
}
