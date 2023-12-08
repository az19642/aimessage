package services.generate_password;

/**
 * A simple data class representing input data for the Password Generator use case.
 * Currently, this class is empty because no input data is needed to generate a password with the API.
 */
public class GeneratePasswordInputData {
    final private String prompt;

    /**
     * Constructs a new PasswordGeneratorInputData instance.
     * Not used because password generation requires no input data from the user.
     */
    public GeneratePasswordInputData(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }
}
