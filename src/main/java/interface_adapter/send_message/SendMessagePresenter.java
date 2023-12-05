package interface_adapter.send_message;


import use_case.send_message.SendMessageOutputBoundary;

/**
 * Presenter responsible for handling the output of the send message
 * use case and updating the corresponding ViewModel.
 */
public class SendMessagePresenter implements SendMessageOutputBoundary {

    /**
     * Prepares the view for successfully sending a message by providing the necessary data.
     *
     * Should just call the message view again to refresh the messages
     */
    @Override
    public void prepareSuccessView() {

    }

}
