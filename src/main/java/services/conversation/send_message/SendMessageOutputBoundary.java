package services.conversation.send_message;

/**
 * Interface representing the output boundary for the send message use case.
 */
public interface SendMessageOutputBoundary {

    /**
     * Prepares the view for successfully sending a message by providing the necessary data.
     * <p>
     * Should just call the message view again to refresh the messages
     */
    void prepareSuccessView();

}
