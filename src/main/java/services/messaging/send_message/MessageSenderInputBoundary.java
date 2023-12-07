package services.messaging.send_message;

/**
 * Interface representing the input boundary for the send message use case.
 */
public interface MessageSenderInputBoundary {

    /**
     * Executes the send message use case with the provided send message input data.
     *
     * @param messageSenderInputData The input data for the send message use case.
     */
    void execute(MessageSenderInputData messageSenderInputData);

}
