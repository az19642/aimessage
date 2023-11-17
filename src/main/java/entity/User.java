package entity;

import java.time.LocalDateTime;
import java.util.List;

public interface User {

    String getName();

    String getPassword();

    String getPreferredLanguage();

    List<Contact> getContacts();

    LocalDateTime getCreationTime();
}
