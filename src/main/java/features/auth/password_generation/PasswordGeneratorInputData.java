package features.auth.password_generation;

/**
 * A simple data class representing input data for the Password Generator use case.
 * Currently, this class is empty because no input data is needed to generate a password with the API.
 */
public class PasswordGeneratorInputData {
    final private String prompt;
    /**
     * Constructs a new PasswordGeneratorInputData instance.
     * Not used because password generation requires no input data from the user.
     */
    public PasswordGeneratorInputData(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }
}
