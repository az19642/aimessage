package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserTest {
    private Contact contact;
    private List<Contact> contacts;
    private CommonUser user;

    @BeforeEach
    void init() {
        List<Message> messages = new ArrayList<>();
        Message hiMessage = new Message("Hello", "Sender", LocalDateTime.now());
        messages.add(hiMessage);

        contact = new Contact("Contact", "EN", LocalDateTime.now(), messages);

        contacts = new ArrayList<>();
        contacts.add(contact);

        user = new CommonUser("Name", "Password123", "English", LocalDateTime.now(), contacts);
    }

    @Test
    void getName() {
        assertEquals(user.getName(), "Name");
    }

    @Test
    void getPassword() {
        assertEquals(user.getPassword(), "Password123");
    }

    @Test
    void getPreferredLanguage() {
        assertEquals(user.getPreferredLanguage(), "English");
    }

    @Test
    void getContacts() {
        assertEquals(user.getContacts(), contacts);
    }

    @Test
    void getCreationTime() {
        assertNotNull(user.getCreationTime());
    }
}
