package use_case.password_generator;

/**
 * The interactor class for the Password Generator use case.
 */
public class PasswordGeneratorInteractor implements PasswordGeneratorInputBoundary {
    final PasswordGeneratorUserDataAccessInterface passwordGeneratorDataAccessObject;
    final PasswordGeneratorOutputBoundary passwordGeneratorPresenter;

    /**
     * Constructs a new PasswordGeneratorInteractor with the provided data access object and presenter.
     *
     * @param passwordGeneratorDataAccessObject The data access object for password generation operations.
     * @param passwordGeneratorOutputBoundary  The presenter for handling output related to the Password Generator use case.
     */
    public PasswordGeneratorInteractor(PasswordGeneratorUserDataAccessInterface passwordGeneratorDataAccessObject,
                                       PasswordGeneratorOutputBoundary passwordGeneratorOutputBoundary) {
        this.passwordGeneratorDataAccessObject = passwordGeneratorDataAccessObject;
        this.passwordGeneratorPresenter = passwordGeneratorOutputBoundary;
    }

    /**
     * Executes the Password Generator use case based on the provided prompt.
     * Prepares the view for a successful response and delegates password generation to the data access object.
     *
     * @param prompt The prompt or request for generating the password.
     * @return The generated password as a string.
     */
    @Override
    public String execute(String prompt) {
        String generatedPassword = passwordGeneratorDataAccessObject.generateSecurePassword(prompt);

        PasswordGeneratorOutputData passwordGeneratorOutputData =
                new PasswordGeneratorOutputData(generatedPassword, false);
        passwordGeneratorPresenter.prepareSuccessView(passwordGeneratorOutputData);

        return generatedPassword;
    }
}
