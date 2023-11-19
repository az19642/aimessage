package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {
    private Contact contact;

    @BeforeEach
    void init() {
        List<Message> messages = new ArrayList<>();
        Message hiMessage = new Message("Hello", "Sender");
        messages.add(hiMessage);

        contact = new Contact("Contact", "EN", LocalDateTime.now(), messages);
    }

    @Test
    void getNameTest() {
        assertEquals("Contact", contact.getName());
    }

    @Test
    void getLastMessageTimeTest() {
        assertNotNull(contact.getLastMessageTime());
    }

    @Test
    void getPreferredLanguageTest() {
        assertEquals("EN", contact.getPreferredLanguage());
    }

    @Test
    void getMessagesTest() {
        List<Message> messages = contact.getMessages();

        assertNotNull(messages);
        assertEquals(1, messages.size());

        Message retrievedMessage = messages.get(0);
        assertEquals("Hello", retrievedMessage.getContent());
        assertEquals("Sender", retrievedMessage.getSender());
    }
}