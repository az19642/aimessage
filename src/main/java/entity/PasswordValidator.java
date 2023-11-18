package entity;

/**
 * Interface for validating password strength.
 */
public interface PasswordValidator {

    /**
     * Validates the strength of a password based on certain criteria.
     *
     * @param password The password to be validated.
     * @return {@code true} if the password is considered strong; {@code false} otherwise.
     */
    public boolean passwordIsValid(String password);

}