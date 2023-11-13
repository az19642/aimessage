package data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.User;
import entity.UserFactory;
import org.bson.Document;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MongoUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {
    private final MongoCollection<Document> userRecords;
    private final Map<String, User> accounts = new HashMap<>();

    public MongoUserDataAccessObject(String adminPassword, UserFactory userFactory) {
        String uri = String.format(
                "mongodb+srv://langAdmin:%s@languality.a8dkfut.mongodb.net/?retryWrites=true&w=majority",
                adminPassword
        );

        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("langualityDB");
        userRecords = database.getCollection("users");

        for (Document doc : userRecords.find()) {
            String name = doc.get("name").toString();
            String password = doc.get("password").toString();
            LocalDateTime creationDate = LocalDateTime.parse((String) doc.get("creationTime"));
            User user = userFactory.create(name, password, creationDate);
            accounts.put(name, user);
        }
    }

    @Override
    public boolean existsByName(String name) {
        return accounts.containsKey(name);
    }

    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);

        Document userDocument = new Document()
                .append("name", user.getName())
                .append("password", user.getPassword())
                .append("creationTime", user.getCreationTime().toString());
        userRecords.insertOne(userDocument);
    }

    @Override
    public User get(String name) {
        return accounts.get(name);
    }
}