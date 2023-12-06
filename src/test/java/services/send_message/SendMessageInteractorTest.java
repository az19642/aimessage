package services.send_message;

import data_access.MongoUserDataAccessObject;
import entities.CommonUserFactory;
import services.messaging.send_message.SendMessageInputData;
import services.messaging.send_message.SendMessageInteractor;
import services.messaging.send_message.SendMessageOutputBoundary;
import org.junit.jupiter.api.Test;

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
