package entity;

/**
 * Implementation of the PasswordValidator interface that validates password strength.
 */
public class PasswordValidatorService implements PasswordValidator {

    /**
     * Validates the strength of a password based on a length criteria.
     *
     * @param password The password being validated.
     * @return {@code true} if the password is considered strong (length > 5); {@code false} otherwise.
     */
    public boolean passwordIsValid(String password) {
        return password != null && password.length() > 5;
    }
}