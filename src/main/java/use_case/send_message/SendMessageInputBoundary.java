package use_case.send_message;

import use_case.mutating_contacts.MutatingContactsInputData;

/**
 * Interface representing the input boundary for the send message use case.
 */
public interface SendMessageInputBoundary {

    /**
     * Executes the send message use case with the provided send message input data.
     *
     * @param sendMessageInputData The input data for the send message use case.
     */
    void execute(SendMessageInputData sendMessageInputData);

}
