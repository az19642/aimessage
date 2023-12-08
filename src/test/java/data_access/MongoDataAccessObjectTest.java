package data_access;

import entities.CommonUserFactory;
import org.junit.jupiter.api.Test;

public class MongoDataAccessObjectTest {

    @Test
    void execute() {

        MongoDataAccessObject mongoDataAccessObject = new MongoDataAccessObject(System.getenv("MONGO_PASSWORD"),
                new CommonUserFactory());

        mongoDataAccessObject.setUser("c", "7yH#91@xT&61");

        mongoDataAccessObject.addContact("cc");

        mongoDataAccessObject.removeContact("cc");

    }

}
