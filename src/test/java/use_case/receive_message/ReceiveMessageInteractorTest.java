package use_case.receive_message;

import data_access.MongoUserDataAccessObject;
import entity.CommonUserFactory;
import org.junit.jupiter.api.Test;
import use_case.recieve_message.ReceiveMessageInteractor;
import use_case.recieve_message.ReceiveMessageOutputBoundary;
import use_case.recieve_message.ReceiveMessageUserDataAccessInterface;

public class ReceiveMessageInteractorTest {

    @Test
    void execute() {
        MongoUserDataAccessObject receiveMessageDataAccessInterface = new MongoUserDataAccessObject(
                System.getenv("MONGO_PASSWORD"),
                new CommonUserFactory()
        ) {
        };
        receiveMessageDataAccessInterface.setUser("test", "test");

        ReceiveMessageOutputBoundary sendMessapePresenter = new ReceiveMessageOutputBoundary() {
            @Override
            public void prepareSuccessView() {

            }
        };

        ReceiveMessageInteractor interactor =
                new ReceiveMessageInteractor(receiveMessageDataAccessInterface, sendMessapePresenter);
        interactor.execute();

    }

}
