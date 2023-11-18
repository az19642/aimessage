package use_case.password_generator;

/**
 * A simple data class representing the output data for the Password Generator use case.
 */
public class PasswordGeneratorOutputData {
    private final String generatedPassword;
    private final boolean useCaseFailed;

    /**
     * Constructs a new PasswordGeneratorOutputData object.
     *
     * @param useCaseFailed A boolean indicating whether the Password Generator use case has failed.
     */
    public PasswordGeneratorOutputData(String generatedPassword, boolean useCaseFailed) {
        this.generatedPassword = generatedPassword;
        this.useCaseFailed = useCaseFailed;
    }

    public String getGeneratedPassword() {
        return generatedPassword;
    }
}
