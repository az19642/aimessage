package services.generate_password;

/**
 * A simple data class representing the output data for the Password Generator use case.
 */
public class GeneratePasswordOutputData {
    private final String generatedPassword;
    private final boolean useCaseFailed;

    /**
     * Constructs a new PasswordGeneratorOutputData object.
     *
     * @param useCaseFailed A boolean indicating whether the Password Generator use case has failed.
     */
    public GeneratePasswordOutputData(String generatedPassword, boolean useCaseFailed) {
        this.generatedPassword = generatedPassword;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Gets the generated password.
     *
     * @return The generated password.
     */
    public String getGeneratedPassword() {
        return generatedPassword;
    }
}
