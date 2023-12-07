package services.signup;

import data_access.MongoSenderUserDataAccessObject;
import entities.CommonUserFactory;
import entities.User;
import entities.UserFactory;
import services.auth.signup.*;
import org.junit.jupiter.api.Test;

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
        SignupInputData inputData = new SignupInputData("testUser2", "testPwd", "testPwd", "English");
        MongoSenderUserDataAccessObject userRepository = new MongoSenderUserDataAccessObject(
                System.getenv("MONGO_PASSWORD"),
                new CommonUserFactory()
        );

        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertEquals("testUser2", user.getUsername());
                assertNotNull(user.getCreationTime());
                assertTrue(userRepository.userExists("testUser2"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory());
        interactor.execute(inputData);

        userRepository.deleteUser("testUser2");
    }

    /**
     * Test the failure scenario where the passwords provided do not match.
     */
    @Test
    void failurePasswordDontMatchTest() {
        SignupInputData inputData = new SignupInputData("testUser2", "testPwd", "testPassword", "English");
        SignupUserDataAccessInterface userRepository = new MongoSenderUserDataAccessObject(
                System.getenv("MONGO_PASSWORD"),
                new CommonUserFactory()
        );

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

        userRepository.deleteUser("testUser2");
    }

    /**
     * Test the failure scenario where the user already exists.
     */
    @Test
    void failureUserExistsTest() {
        SignupInputData inputData = new SignupInputData("testUser2", "testPwd", "testPwd", "English");
        SignupUserDataAccessInterface userRepository = new MongoSenderUserDataAccessObject(
                System.getenv("MONGO_PASSWORD"),
                new CommonUserFactory()
        );

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("testUser2", "testPwd", "English" ,LocalDateTime.now(), new ArrayList<>());
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

        userRepository.deleteUser("testUser2");
    }
}