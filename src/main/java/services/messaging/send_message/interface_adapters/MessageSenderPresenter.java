package services.messaging.send_message.interface_adapters;


import services.messaging.send_message.MessageSenderOutputBoundary;

/**
 * Presenter responsible for handling the output of the send message
 * use case and updating the corresponding ViewModel.
 */
public class MessageSenderPresenter implements MessageSenderOutputBoundary {

    /**
     * Prepares the view for successfully sending a message by providing the necessary data.
     *
     * Should just call the message view again to refresh the messages
     */
    @Override
    public void prepareSuccessView() {

    }

}
