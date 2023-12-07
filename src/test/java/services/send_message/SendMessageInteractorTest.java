package services.send_message;

import data_access.MongoDataAccessObject;
import entities.CommonUserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class SendMessageInteractorTest {

    @Test
    void execute() {
        MessageSenderInputData inputData = new MessageSenderInputData("testa", "this is a test");
        MongoDataAccessObject sendMessageDataAccessInterface = new MongoDataAccessObject(
                System.getenv("MONGO_PASSWORD"),
                new CommonUserFactory()
        );
        sendMessageDataAccessInterface.setUser("test", "test");
        sendMessageDataAccessInterface.addContact("testa");

        MessageSenderOutputBoundary sendMessapePresenter = new MessageSenderOutputBoundary() {
            @Override
            public void prepareSuccessView() {

            }
        };

        MessageSenderInteractor interactor =
                new MessageSenderInteractor(sendMessageDataAccessInterface, sendMessapePresenter);
        interactor.execute(inputData);

    }

}
