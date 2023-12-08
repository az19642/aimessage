package services.conversation.send_message;

public class SendMessageInteractor implements SendMessageInputBoundary {
    final SendMessageDataAccessInterface sendMessageDataAccessInterface;
    final SendMessageOutputBoundary sendMessagePresenter;

    public SendMessageInteractor(SendMessageDataAccessInterface sendMessageDataAccessInterface,
                                 SendMessageOutputBoundary sendMessageOutputBoundary) {
        this.sendMessageDataAccessInterface = sendMessageDataAccessInterface;
        this.sendMessagePresenter = sendMessageOutputBoundary;
    }

    /**
     * Executes the send message use case with the provided content of the message and the contact's name.
     *
     * @param sendMessageInputData The input data for the send message use case.
     */
    @Override
    public void execute(SendMessageInputData sendMessageInputData) {

        sendMessageDataAccessInterface.sendMessage(sendMessageInputData.getContactName(),
                sendMessageInputData.getContent());

        sendMessagePresenter.prepareSuccessView();


    }
}
