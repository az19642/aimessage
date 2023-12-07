package services.messaging.send_message;

public class MessageSenderInteractor implements MessageSenderInputBoundary {
    final MessageSenderUserDataAccessInterface messageSenderUserDataAccessInterface;
    final MessageSenderOutputBoundary sendMessagePresenter;

    public MessageSenderInteractor(MessageSenderUserDataAccessInterface messageSenderUserDataAccessInterface,
                                   MessageSenderOutputBoundary messageSenderOutputBoundary) {
        this.messageSenderUserDataAccessInterface = messageSenderUserDataAccessInterface;
        this.sendMessagePresenter = messageSenderOutputBoundary;
    }

    /**
     * Executes the send message use case with the provided content of the message and the contact's name.
     *
     * @param messageSenderInputData The input data for the send message use case.
     */
    @Override
    public void execute(MessageSenderInputData messageSenderInputData) {

        messageSenderUserDataAccessInterface.sendMessage(messageSenderInputData.getContactName(),
                messageSenderInputData.getContent());

        sendMessagePresenter.prepareSuccessView();


    }
}
