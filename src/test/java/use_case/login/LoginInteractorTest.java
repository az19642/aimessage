package use_case.login;

import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for the {@link LoginInteractor} class.
 */
class LoginInteractorTest {

    /**
     * Tests the success scenario where the provided username and password are correct.
     */
    @Test
    void successTest() {
        UserFactory userFactory = new CommonUserFactory();

        // Create a mock data access interface that returns a user with the given username and password.
        LoginUserDataAccessInterface userDataAccessInterface = new LoginUserDataAccessInterface() {
            @Override
            public User getUser(String username, String password) {
                return userFactory.create(
                        "andy123",
                        "password123",
                        "English",
                        LocalDateTime.now(),
                        new ArrayList<>()
                );
            }
        };

        // Create a mock output boundary that asserts the correct username is returned.
        LoginOutputBoundary loginOutputBoundary = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData loginOutputData) {
                assert loginOutputData.getUsername().equals("andy123");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LoginInteractor loginInteractor = new LoginInteractor(userDataAccessInterface, loginOutputBoundary);
        LoginInputData loginInputData = new LoginInputData("andy123", "password123");

        loginInteractor.execute(loginInputData);
    }

    /**
     * Tests the failure scenario where the provided username or password is incorrect.
     */
    @Test
    void failureUsernameOrPasswordIsIncorrect() {
        UserFactory userFactory = new CommonUserFactory();

        // Create a mock data access interface that returns null when the username is queried.
        LoginUserDataAccessInterface userDataAccessInterface = new LoginUserDataAccessInterface() {
            @Override
            public User getUser(String username, String password) {
                return null;
            }
        };

        // Create a mock output boundary that asserts the correct error message is returned.
        LoginOutputBoundary loginOutputBoundary = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData loginOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assert error.equals("Incorrect username or password");
            }
        };

        LoginInteractor loginInteractor = new LoginInteractor(userDataAccessInterface, loginOutputBoundary);
        LoginInputData loginInputData = new LoginInputData("andy123", "password123");

        loginInteractor.execute(loginInputData);
    }
}
