package use_case.login;

import entity.User;

/**
 * Implements the login use case.
 */
public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    /**
     * Executes the login use case with the provided login input data.
     *
     * @param loginInputData The input data for the login use case.
     */
    @Override
    public void execute(LoginInputData loginInputData) {
        User user = userDataAccessObject.getUser(loginInputData.getUsername(), loginInputData.getPassword());
        if (user == null) {
            loginPresenter.prepareFailView("Incorrect username or password");
        } else {
            LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
            loginPresenter.prepareSuccessView(loginOutputData);
        }
    }
}