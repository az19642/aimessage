package data_access;

import com.mongodb.client.model.IndexOptions;
import entity.*;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;

import static com.mongodb.client.model.Filters.eq;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * This class is the data access objects which deals with all interactions with the database
 * The software used is MongoDB
 * There are three collections: Users, Conversations, and Messages
 * These three collections have an important relationship
 * The Users collection has a field which is a mapping of contacts to conversation ids
 * The Conversation collection has a field which is a list of message ids in order from oldest to newest message
 *
 */

public class MongoUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {
    private final MongoCollection<Document> userRecords;
    private final MongoCollection<Document> conversationRecords;
    private final MongoCollection<Document> messageRecords;
    private final UserFactory userFactory;
    private final ContactFactory contactFactory;
    private final MessageFactory messageFactory;

    public MongoUserDataAccessObject(String adminPassword, UserFactory userFactory) {
        String uri = String.format(
                "mongodb+srv://langAdmin:%s@languality.a8dkfut.mongodb.net/?retryWrites=true&w=majority",
                adminPassword
        );

        this.userFactory = userFactory;
        this.contactFactory = new ContactFactory();
        this.messageFactory = new MessageFactory();

        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("langualityDB");
        userRecords = database.getCollection("users");
        conversationRecords = database.getCollection("conversations");
        messageRecords = database.getCollection("messages");

        // This code will make the look-up faster
        userRecords.createIndex(new Document("name", 1), new IndexOptions().unique(true));
        conversationRecords.createIndex(new Document("ID", 1), new IndexOptions().unique(true));
        messageRecords.createIndex(new Document("ID", 1), new IndexOptions().unique(true));
    }


    /**
     *
     * Saves a new user to the database, notice that the contacts map will be empty
     * Do not use this to update an existing user, use the update method instead.
     *
     * @param user a new user with the name, password, and preferred language attributes filled in.
     *
     */
    @Override
    public void save(User user) {

        Document userDocument = new Document()
                .append("name", user.getName())
                .append("password", user.getPassword())
                .append("preferredLanguage", user.getPreferredLanguage())
                .append("creationTime", LocalDateTime.now())
                .append("contactToChatID", new HashMap<>());

        userRecords.insertOne(userDocument);

    }


    /**
     *
     * Updates an existing user, call this method whenever a change has been made to user
     * For example when a new contact is added or removed.
     * Notice that this method is private as whenever a change is made
     *
     * @param user an existing user in the database
     *
     */
    private void update(User user) {

    }


    /**
     * Returns whether a user with the given username exists
     *
     * @param name the username to be checked
     * @return true if the user exists, false otherwise
     */
    @Override
    public boolean userExists(String name) {

        return userRecords.find(eq("name", name)).first() != null;
    }


    /**
     * Adds a contact to the user, and will also update the contact such that the user will be a contact for them.
     * Precondition: The given username is a valid contact.
     *
     * @param user the current user
     * @param contactName the contacts name
     */
    public void addContact(User user, String contactName) {

    }


    /**
     * This method should be called when the user would like to log in
     * If the username and password do not match a user, or the username is not valid null will be returned
     * Otherwise it will return the user with the given username.
     *
     * @param name the username of the user
     * @param password the password of the user
     * @return User with given username and password if valid, otherwise return null
     */
    @Override
    public User getUser(String name, String password) {

        Document dbUser = userRecords.find(eq("name", name)).first();

        if (dbUser == null || !dbUser.getString("password").equals(password)) {
            return null;
        }

        // Fetch the contacts information, which includes the conversation
        ArrayList<Contact> contacts = new ArrayList<>();

        Object contactToChatIDdb = dbUser.get("contactToChatID");

        if (contactToChatIDdb instanceof Document contactsDocument) {

            // Convert the BSON Document back to a HashMap
            HashMap<String, Object> contactToChatID = new HashMap<>(contactsDocument);

            // Each iteration will configure one contact of the user contact list
            for (String contact: contactToChatID.keySet()) {
                Document contactDoc = userRecords.find(eq("name", contact)).first();
                Document convoDoc = conversationRecords.find(eq("ID", contactToChatID.get(contact))).first();

                ArrayList<Message> messages = new ArrayList<>();

                // List of message ids between the current contact and the user
                List<Integer> messagesIDs = convoDoc.getList("messagesIDs", Integer.class);

                // Iterates over all message Ids finding the message and converting it to the message entity
                for (Integer messageID: messagesIDs) {
                    Document messageDoc = messageRecords.find(eq("ID", messageID)).first();
                    messages.add(messageFactory.create(messageDoc.getString("content"),
                            messageDoc.getString("sender")));
                }

                LocalDateTime lastMessageTime =  convoDoc.getDate("lastMessageTime").toInstant().
                        atZone(ZoneId.systemDefault()).toLocalDateTime();

                // Combining all the efforts now to create a contact
                contacts.add(contactFactory.create(contact, contactDoc.getString("preferredLanguage"),
                        lastMessageTime, messages));

            }
        } else {
            System.out.println("The 'contacts' field does not contain a HashMap.");
            return null;
        }

        // Creates the user getting the string and date information
        return userFactory.create(name, password,
                dbUser.getString("preferredLanguage"), dbUser.getDate("creationTime").toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime(), contacts);
    }
}