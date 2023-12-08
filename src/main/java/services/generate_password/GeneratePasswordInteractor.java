package services.generate_password;

/**
 * The interactor class for the Password Generator use case.
 */
public class GeneratePasswordInteractor implements GeneratePasswordInputBoundary {
    final GeneratePasswordDataAccessInterface passwordGeneratorDataAccessObject;
    final GeneratePasswordOutputBoundary passwordGeneratorPresenter;

    /**
     * Constructs a new PasswordGeneratorInteractor with the provided data access object and presenter.
     *
     * @param passwordGeneratorDataAccessObject The data access object for password generation operations.
     * @param generatePasswordOutputBoundary    The presenter for handling output related to the Password Generator use case.
     */
    public GeneratePasswordInteractor(GeneratePasswordDataAccessInterface passwordGeneratorDataAccessObject,
                                      GeneratePasswordOutputBoundary generatePasswordOutputBoundary) {
        this.passwordGeneratorDataAccessObject = passwordGeneratorDataAccessObject;
        this.passwordGeneratorPresenter = generatePasswordOutputBoundary;
    }

    /**
     * Executes the Password Generator use case based on the provided prompt.
     * Prepares the view for a successful response and delegates password generation to the data access object.
     *
     * @param generatePasswordInputData The prompt or request for generating the password.
     */
    @Override
    public void execute(GeneratePasswordInputData generatePasswordInputData) {
        String generatedPassword = passwordGeneratorDataAccessObject.generateSecurePassword(
                generatePasswordInputData.getPrompt());

        GeneratePasswordOutputData generatePasswordOutputData = new GeneratePasswordOutputData(generatedPassword,
                false);
        passwordGeneratorPresenter.prepareSuccessView(generatePasswordOutputData);
    }
}
