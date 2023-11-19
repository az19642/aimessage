package interface_adapter.password_generator;

/**
 * The state class that holds information related to the Password Generator interface, including error messages.
 */
public class PasswordGeneratorState {

    private String passwordGeneratorError = null;

    /**
     * Constructs a new PasswordGeneratorState by copying the content from an existing state.
     *
     * @param copy The PasswordGeneratorState to copy the content from.
     */
    public PasswordGeneratorState(PasswordGeneratorState copy) {
        passwordGeneratorError = copy.passwordGeneratorError;
    }

    public PasswordGeneratorState() {
    }

    /**
     * Gets the error message associated with the Password Generator.
     *
     * @return The error message as a string.
     */
    public String getPasswordGeneratorError() {
        return passwordGeneratorError;
    }

    /**
     * Sets the error message associated with the Password Generator.
     *
     * @param passwordGeneratorError The error message to set.
     */
    public void setPasswordGeneratorError(String passwordGeneratorError) {
        this.passwordGeneratorError = passwordGeneratorError;
    }

    /**
     * Returns a string representation of the PasswordGeneratorState.
     *
     * @return A string representation that includes the error message.
     */
    @Override
    public String toString() {
        return "PasswordGeneratorState{" +
                "passwordGeneratorError='" + passwordGeneratorError + '\'' +
                '}';
    }
}
