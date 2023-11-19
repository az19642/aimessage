package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void getMessageTime() {
        Message hiMessage = new Message("Hello", "Sender");
        assertNotNull(hiMessage.getMessageTime());
    }
}