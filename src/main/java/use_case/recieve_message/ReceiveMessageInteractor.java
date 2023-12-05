package use_case.recieve_message;

import entity.User;

public class ReceiveMessageInteractor implements ReceiveMessageInputBoundary{

    final ReceiveMessageUserDataAccessInterface receiveMessageUserDataAccessInterface;
    final ReceiveMessageOutputBoundary receiveMessagePresenter;

    public ReceiveMessageInteractor(ReceiveMessageUserDataAccessInterface receiveMessageUserDataAccessInterface,
                                    ReceiveMessageOutputBoundary receiveMessagePresenter) {
        this.receiveMessageUserDataAccessInterface = receiveMessageUserDataAccessInterface;
        this.receiveMessagePresenter = receiveMessagePresenter;
    }

    /**
     * Executes the use case receive message with the provided content of the message and the contact's name.
     */
    @Override
    public void execute() {

        User user = receiveMessageUserDataAccessInterface.getUser();
        receiveMessageUserDataAccessInterface.setUser(user.getName(), user.getPassword());

        receiveMessagePresenter.prepareSuccessView();


    }

}
