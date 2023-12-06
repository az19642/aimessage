package features.auth.password_generation.interface_adapters;

import features.auth.password_generation.PasswordGeneratorOutputBoundary;
import features.auth.password_generation.PasswordGeneratorOutputData;
import interface_adapter.ViewManagerModel;

/**
 * The presenter class responsible for handling the presentation logic related to the Password Generator use case.
 */
public class PasswordGeneratorPresenter implements PasswordGeneratorOutputBoundary {

    private final PasswordGeneratorViewModel passwordGeneratorViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a new PasswordGeneratorPresenter with the provided ViewManagerModel and PasswordGeneratorViewModel.
     *
     * @param viewManagerModel           The model responsible for managing views in the application.
     * @param passwordGeneratorViewModel The view model for the Password Generator interface.
     */
    public PasswordGeneratorPresenter(ViewManagerModel viewManagerModel,
                                      PasswordGeneratorViewModel passwordGeneratorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.passwordGeneratorViewModel = passwordGeneratorViewModel;
    }

    /**
     * Prepares the view for a successful response from the Password Generator use case.
     * Updates the view model and view manager to reflect the success state.
     *
     * @param response The output data from the Password Generator use case.
     */
    @Override
    public void prepareSuccessView(PasswordGeneratorOutputData response) {
        PasswordGeneratorState passwordGeneratorState = passwordGeneratorViewModel.getState();
        passwordGeneratorState.setGeneratedPassword(response.getGeneratedPassword());
        this.passwordGeneratorViewModel.setState(passwordGeneratorState);
        this.passwordGeneratorViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(passwordGeneratorViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view for a failed response from the Password Generator use case.
     *
     * @param error The error message associated with the failed operation.
     */
    @Override
    public void prepareFailView(String error) {
        PasswordGeneratorState passwordGeneratorState = passwordGeneratorViewModel.getState();
        passwordGeneratorState.setPasswordGeneratorError(error);
        viewManagerModel.firePropertyChanged();
    }
}
