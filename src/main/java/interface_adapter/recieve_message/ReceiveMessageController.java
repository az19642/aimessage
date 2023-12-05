package interface_adapter.recieve_message;

import use_case.recieve_message.ReceiveMessageInputBoundary;

/**
 * Controller responsible for handling receiving message interactions and invoking the corresponding use case.
 */
public class ReceiveMessageController {
    final ReceiveMessageInputBoundary receiveMessageInputBoundary;

    public ReceiveMessageController(ReceiveMessageInputBoundary receiveMessageInputBoundary) {
        this.receiveMessageInputBoundary = receiveMessageInputBoundary;
    }

    /**
     * Executes the send message action with the given content and the contact name to send the message to.
     * Converts the input parameters into a SendMessageInputData object and assigns the execution to the use case.
     */
    public void execute() {
        receiveMessageInputBoundary.execute();
    }
}
