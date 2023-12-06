package services.auth.login;

import entities.User;

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
        userDataAccessObject.setUser(loginInputData.getUsername(), loginInputData.getPassword());
        User user = userDataAccessObject.getUser();
        if (user == null) {
            loginPresenter.prepareFailView("Incorrect username or password");
        } else {
            LoginOutputData loginOutputData = new LoginOutputData(user.getName());
            loginPresenter.prepareSuccessView(loginOutputData);
        }
    }
}