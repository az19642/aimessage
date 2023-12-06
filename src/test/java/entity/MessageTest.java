package entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void getMessageTime() {
        Message hiMessage = new Message("Hello", "Sender", LocalDateTime.now());
        assertNotNull(hiMessage.getMessageTime());
    }

    @Test
    void getSender() {
        Message hiMessage = new Message("Hello", "Sender", LocalDateTime.now());
        assertEquals(hiMessage.getSender(), "Sender");
    }

    @Test
    void getContent() {
        Message hiMessage = new Message("Hello", "Sender", LocalDateTime.now());
        assertEquals(hiMessage.getContent(), "Hello");
    }
}
