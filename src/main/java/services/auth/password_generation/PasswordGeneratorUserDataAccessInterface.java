package services.auth.password_generation;

/**
 * The interface for the data access component responsible for password generation operations.
 */
public interface PasswordGeneratorUserDataAccessInterface {
    String generateSecurePassword(String prompt);
}
