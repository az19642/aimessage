package use_case.signup;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link SignupInteractor} class, covering different scenarios.
 */
class SignupInteractorTest {

    /**
     * Test the successful execution of the signup use case.
     */
    @Test
    void successTest() {
        SignupInputData inputData = new SignupInputData("user", "pass", "pass", "EN");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertEquals("user", user.getUsername());
                assertNotNull(user.getCreationTime());
                assertTrue(userRepository.userExists("user"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    /**
     * Test the failure scenario where the passwords provided do not match.
     */
    @Test
    void failurePasswordDontMatchTest() {
        SignupInputData inputData = new SignupInputData("user", "pass", "password", "EN");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use Case success is not expected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    /**
     * Test the failure scenario where the user already exists.
     */
    @Test
    void failureUserExistsTest() {
        SignupInputData inputData = new SignupInputData("user", "pass", "pass", "EN");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("user", "password", "EN" ,LocalDateTime.now(), new ArrayList<>());
        userRepository.save(user);

        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }
}