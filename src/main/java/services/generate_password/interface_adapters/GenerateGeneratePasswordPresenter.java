package services.generate_password.interface_adapters;

import interface_adapters.ViewManagerModel;
import services.generate_password.GeneratePasswordOutputBoundary;
import services.generate_password.GeneratePasswordOutputData;

/**
 * The presenter class responsible for handling the presentation logic related to the Password Generator use case.
 */
public class GenerateGeneratePasswordPresenter implements GeneratePasswordOutputBoundary {

    private final GeneratePasswordViewModel generatePasswordViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a new PasswordGeneratorPresenter with the provided ViewManagerModel and PasswordGeneratorViewModel.
     *
     * @param viewManagerModel          The model responsible for managing views in the application.
     * @param generatePasswordViewModel The view model for the Password Generator interface.
     */
    public GenerateGeneratePasswordPresenter(ViewManagerModel viewManagerModel,
                                             GeneratePasswordViewModel generatePasswordViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.generatePasswordViewModel = generatePasswordViewModel;
    }

    /**
     * Prepares the view for a successful response from the Password Generator use case.
     * Updates the view model and view manager to reflect the success state.
     *
     * @param response The output data from the Password Generator use case.
     */
    @Override
    public void prepareSuccessView(GeneratePasswordOutputData response) {
        GeneratePasswordState generatePasswordState = generatePasswordViewModel.getState();
        generatePasswordState.setGeneratedPassword(response.getGeneratedPassword());
        this.generatePasswordViewModel.setState(generatePasswordState);
        this.generatePasswordViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(generatePasswordViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view for a failed response from the Password Generator use case.
     *
     * @param error The error message associated with the failed operation.
     */
    @Override
    public void prepareFailView(String error) {
        GeneratePasswordState generatePasswordState = generatePasswordViewModel.getState();
        generatePasswordState.setPasswordGeneratorError(error);
        viewManagerModel.firePropertyChanged();
    }
}
