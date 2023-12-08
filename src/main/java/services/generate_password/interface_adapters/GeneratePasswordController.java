package services.generate_password.interface_adapters;

import services.generate_password.GeneratePasswordInputBoundary;
import services.generate_password.GeneratePasswordInputData;

/**
 * The controller class responsible for executing the Password Generator use case.
 */
public class GeneratePasswordController {

    private final GeneratePasswordInputBoundary passwordGeneratorUseCaseInteractor;

    /**
     * Constructs a new PasswordGeneratorController with the provided PasswordGeneratorInputBoundary.
     *
     * @param passwordGeneratorUseCaseInteractor The interactor responsible for generating passwords.
     */
    public GeneratePasswordController(GeneratePasswordInputBoundary passwordGeneratorUseCaseInteractor) {
        this.passwordGeneratorUseCaseInteractor = passwordGeneratorUseCaseInteractor;
    }

    /**
     * Executes the Password Generator use case to generate a password based on the given prompt.
     *
     * @param prompt The prompt or request for generating the password.
     */
    public void execute(String prompt) {
        GeneratePasswordInputData generatePasswordInputData = new GeneratePasswordInputData(prompt);
        passwordGeneratorUseCaseInteractor.execute(generatePasswordInputData);
    }

}
