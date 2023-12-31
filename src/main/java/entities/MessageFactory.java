package entities;


import java.time.LocalDateTime;

/**
 * This class is a factory class for the message class
 */
public class MessageFactory {

    /**
     * @param content the content of the message
     * @param sender  the name of the sender of the message
     * @return an instance of the message class
     */
    public Message create(String content, String sender, LocalDateTime messageTime) {
        return new Message(content, sender, messageTime);
    }
}
