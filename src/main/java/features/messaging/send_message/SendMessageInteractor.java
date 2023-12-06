package features.messaging.send_message;

public class SendMessageInteractor implements SendMessageInputBoundary {
    final SendMessageUserDataAccessInterface sendMessageUserDataAccessInterface;
    final SendMessageOutputBoundary sendMessagePresenter;

    public SendMessageInteractor(SendMessageUserDataAccessInterface sendMessageUserDataAccessInterface,
                                 SendMessageOutputBoundary sendMessageOutputBoundary) {
        this.sendMessageUserDataAccessInterface = sendMessageUserDataAccessInterface;
        this.sendMessagePresenter = sendMessageOutputBoundary;
    }

    /**
     * Executes the send message use case with the provided content of the message and the contact's name.
     *
     * @param sendMessageInputData The input data for the send message use case.
     */
    @Override
    public void execute(SendMessageInputData sendMessageInputData) {

        sendMessageUserDataAccessInterface.sendMessage(sendMessageInputData.getContactName(),
                sendMessageInputData.getContent());

        sendMessagePresenter.prepareSuccessView();


    }
}
