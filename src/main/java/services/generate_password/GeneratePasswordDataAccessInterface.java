package services.generate_password;

/**
 * The interface for the data access component responsible for password generation operations.
 */
public interface GeneratePasswordDataAccessInterface {
    String generateSecurePassword(String prompt);
}
