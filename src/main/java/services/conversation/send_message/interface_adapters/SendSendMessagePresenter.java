package services.conversation.send_message.interface_adapters;


import services.conversation.send_message.SendMessageOutputBoundary;

/**
 * Presenter responsible for handling the output of the send message
 * use case and updating the corresponding ViewModel.
 */
public class SendSendMessagePresenter implements SendMessageOutputBoundary {

    /**
     * Prepares the view for successfully sending a message by providing the necessary data.
     * <p>
     * Should just call the message view again to refresh the messages
     */
    @Override
    public void prepareSuccessView() {

    }

}
