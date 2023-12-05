package use_case.send_message;

import data_access.MongoUserDataAccessObject;
import entity.CommonUserFactory;
import org.junit.jupiter.api.Test;
import use_case.mutating_contacts.MutatingContactsInputData;
import use_case.mutating_contacts.MutatingContactsInteractor;
import use_case.mutating_contacts.MutatingContactsOutputBoundary;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

public class SendMessageInteractorTest {

    @Test
    void execute() {
        SendMessageInputData inputData = new SendMessageInputData("This is a test", "test2");
        MongoUserDataAccessObject sendMessageDataAccessInterface = new MongoUserDataAccessObject(
                System.getenv("MONGO_PASSWORD"),
                new CommonUserFactory()
        );
        sendMessageDataAccessInterface.setUser("test", "test");

        SendMessageOutputBoundary sendMessapePresenter = new SendMessageOutputBoundary() {
            @Override
            public void prepareSuccessView() {

            }
        };

        SendMessageInteractor interactor =
                new SendMessageInteractor(sendMessageDataAccessInterface, sendMessapePresenter);
        interactor.execute(inputData);

    }

}
