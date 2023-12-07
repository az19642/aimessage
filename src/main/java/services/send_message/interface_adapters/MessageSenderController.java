package services.send_message.interface_adapters;

import services.send_message.MessageSenderInputBoundary;
import services.send_message.MessageSenderInputData;

/**
 * Controller responsible for handling send message interactions and invoking the corresponding use case.
 */
public class MessageSenderController {

    final MessageSenderInputBoundary messageSenderInputBoundary;

    public MessageSenderController(MessageSenderInputBoundary messageSenderInputBoundary) {
        this.messageSenderInputBoundary = messageSenderInputBoundary;
    }

    /**
     * Executes the send message action with the given content and the contact name to send the message to.
     * Converts the input parameters into a SendMessageInputData object and assigns the execution to the use case.
     *
     * @param content The content of the message to send.
     */
    public void execute(String contactName, String content) {
        MessageSenderInputData messageSenderInputData = new MessageSenderInputData(contactName, content);

        messageSenderInputBoundary.execute(messageSenderInputData);
    }

}
