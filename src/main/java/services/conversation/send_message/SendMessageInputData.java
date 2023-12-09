package services.conversation.send_message;

public class SendMessageInputData {

    final private String content;
    final private String contactName;

    public SendMessageInputData(String contactName, String content) {
        this.content = content;
        this.contactName = contactName;
    }

    /**
     * Gets the content of the message to send.
     *
     * @return The message content.
     */
    String getContent() {
        return content;
    }

    /**
     * Gets the contact's name to whom the message is sent to.
     *
     * @return The contact name.
     */
    String getContactName() {
        return contactName;
    }

}
