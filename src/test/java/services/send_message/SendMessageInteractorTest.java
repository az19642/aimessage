package services.send_message;

import data_access.MongoDataAccessObject;
import entities.CommonUserFactory;
import org.junit.jupiter.api.Test;
import services.conversation.send_message.SendMessageInputData;
import services.conversation.send_message.SendMessageInteractor;
import services.conversation.send_message.SendMessageOutputBoundary;

public class SendMessageInteractorTest {

    @Test
    void execute() {
        SendMessageInputData inputData = new SendMessageInputData("testa", "this is a test");
        MongoDataAccessObject sendMessageDataAccessInterface = new MongoDataAccessObject(
                System.getenv("MONGO_PASSWORD"), new CommonUserFactory());
        sendMessageDataAccessInterface.setUser("test", "test");
        sendMessageDataAccessInterface.addContact("testa");

        SendMessageOutputBoundary sendMessapePresenter = new SendMessageOutputBoundary() {
            @Override
            public void prepareSuccessView() {

            }
        };

        SendMessageInteractor interactor = new SendMessageInteractor(sendMessageDataAccessInterface,
                sendMessapePresenter);
        interactor.execute(inputData);

    }

}
