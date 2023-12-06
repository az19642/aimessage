package services.messaging.send_message.interface_adapters;

import services.messaging.send_message.SendMessageInputBoundary;
import services.messaging.send_message.SendMessageInputData;

/**
 * Controller responsible for handling send message interactions and invoking the corresponding use case.
 */
public class SendMessageController {

    final SendMessageInputBoundary sendMessageInputBoundary;

    public SendMessageController(SendMessageInputBoundary sendMessageInputBoundary) {
        this.sendMessageInputBoundary = sendMessageInputBoundary;
    }

    /**
     * Executes the send message action with the given content and the contact name to send the message to.
     * Converts the input parameters into a SendMessageInputData object and assigns the execution to the use case.
     *
     * @param content The content of the message to send.
     */
    public void execute(String contactName, String content) {
        SendMessageInputData sendMessageInputData = new SendMessageInputData( contactName, content);

        sendMessageInputBoundary.execute(sendMessageInputData);
    }

}
