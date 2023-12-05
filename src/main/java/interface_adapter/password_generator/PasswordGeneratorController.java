package interface_adapter.password_generator;

import use_case.password_generator.PasswordGeneratorInputBoundary;

/**
 * The controller class responsible for executing the Password Generator use case.
 */
public class PasswordGeneratorController {

    private final PasswordGeneratorInputBoundary passwordGeneratorUseCaseInteractor;

    /**
     * Constructs a new PasswordGeneratorController with the provided PasswordGeneratorInputBoundary.
     *
     * @param passwordGeneratorUseCaseInteractor The interactor responsible for generating passwords.
     */
    public PasswordGeneratorController(PasswordGeneratorInputBoundary passwordGeneratorUseCaseInteractor) {
        this.passwordGeneratorUseCaseInteractor = passwordGeneratorUseCaseInteractor;
    }

    /**
     * Executes the Password Generator use case to generate a password based on the given prompt.
     *
     * @param prompt The prompt or request for generating the password.
     */
    public void execute(String prompt) {
        passwordGeneratorUseCaseInteractor.execute(prompt);
    }

}
