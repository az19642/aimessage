package data_access;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import entities.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import services.contacts_view_sync.LoadContactsToViewDataAccessInterface;
import services.conversation.view_sync.ConversationSyncDataAccessInterface;
import services.login.LoginUserDataAccessInterface;
import services.contact_mutation.MutatingContactsUserDataAccessInterface;
import services.send_message.MessageSenderUserDataAccessInterface;
import services.signup.SignupUserDataAccessInterface;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * This class is the data access objects which deals with all interactions with the database
 * The software used is MongoDB
 * There are three collections: Users, Conversations, and Messages
 * These three collections have an important relationship
 * The Users collection has a field which is a mapping of contacts to conversation ids
 * The Conversation collection has a field which is a list of message ids in order from oldest to newest message
 */

public class MongoDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface, MutatingContactsUserDataAccessInterface, MessageSenderUserDataAccessInterface, LoadContactsToViewDataAccessInterface, ConversationSyncDataAccessInterface {
    private final MongoCollection<Document> userRecords;
    private final MongoCollection<Document> conversationRecords;
    private final MongoCollection<Document> messageRecords;
    private final UserFactory userFactory;
    private final ContactFactory contactFactory;
    private final MessageFactory messageFactory;
    private User user;

    public MongoDataAccessObject(String adminPassword, UserFactory userFactory) {
        String uri = String.format(
                "mongodb+srv://langAdmin:%s@languality.a8dkfut.mongodb.net/?retryWrites=true&w=majority",
                adminPassword
        );

        this.userFactory = userFactory;
        this.contactFactory = new ContactFactory();
        this.messageFactory = new MessageFactory();
        this.user = null;

        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("langualityDB");
        userRecords = database.getCollection("users");
        conversationRecords = database.getCollection("conversations");
        messageRecords = database.getCollection("messages");

        // This code will make the look-up faster
        userRecords.createIndex(new Document("name", 1), new IndexOptions().unique(true));
        conversationRecords.createIndex(new Document("_id", 1));
        messageRecords.createIndex(new Document("_id", 1));

    }


    /**
     * Saves a new user to the database, notice that the contacts map will be empty
     * Do not use this to update an existing user, use the update method instead.
     *
     * @param user a new user with the name, password, and preferred language attributes filled in.
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
     * <p>
     * There are four cases for the state after this function returns
     * 1. Contact successfully added
     * 2. Contact does not exist in database
     * 3. Contact is already a contact to the user
     * 4. Unexpected error occurred
     *
     * @return A string in {"PASS", "USER DNE", "ALREADY A CONTACT", "FAILED", "CANNOT ADD SELF"}
     */
    @Override
    public String addContact(String contactName) {

        // If the contact is them self
        if (contactName.equals(user.getName())) {
            return "CANNOT ADD SELF";
        }

        // Fetch the contact and user
        Document contact = userRecords.find(eq("name", contactName)).first();
        Document userDB = userRecords.find(eq("name", user.getName())).first();

        // If the contact does not exist
        if (contact == null) {
            return "USER DNE";
        }

        // Retrieving hash map of contacts to convo id
        Object contactToChatIDOfContactdb = contact.get("contactToChatID");
        Object contactToChatIDOfUserdb = userDB.get("contactToChatID");

        if (contactToChatIDOfContactdb instanceof Document contactsOfContactDocument
                && contactToChatIDOfUserdb instanceof Document contactsOfUserDocument) {

            // Convert the BSON Document back to a HashMap
            HashMap<String, Object> contactToChatIDOfContact = new HashMap<>(contactsOfContactDocument);
            HashMap<String, Object> contactToChatIDOfUser = new HashMap<>(contactsOfUserDocument);

            // Checks whether they are already contacts
            if (contactToChatIDOfContact.containsKey(user.getName())) {
                return "ALREADY A CONTACT";
            }

            // Add conversation to database and fetch its ID
            ObjectId id = addConversation();


            contactToChatIDOfContact.put(user.getName(), id);
            contactToChatIDOfUser.put(contactName, id);

            // Adds user to the map of contacts to the contact
            updateContactsDB(contactToChatIDOfContact, contact);

            // Adds the contact to the map of contacts of the user
            updateContactsDB(contactToChatIDOfUser, userDB);

            // Update to the in memory list of contacts
            user.getContacts().add(contactFactory.create(contactName,
                    contact.getString("preferredLanguage"), LocalDateTime.now(), new ArrayList<>()));

            return "PASS";

        }
        // Should never enter here
        return "FAILED";
    }

    /**
     * A helper function to update the contacts field of a given user in the database
     *
     * @param contactToChatID The map mapping the contact name to the corresponding conversation ID
     * @param user The user in the database entry to be updated
     */
    private void updateContactsDB(HashMap<String, Object> contactToChatID, Document user) {

        // Creates instructions to update the contact to chat id field
        Bson updates = Updates.combine(
                Updates.set("contactToChatID", contactToChatID)
        );

        // Instructs the driver to insert a new document if none match the query
        UpdateOptions options = new UpdateOptions().upsert(true);

        try {
            // Updates the first document with the username, so it will only update one
            userRecords.updateOne(user, updates, options);

        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    /**
     * Preconditions:
     * - The contact is in the database
     * - The contact is one of the users contacts
     * <p>
     * Deletes a contact of the user, and will also update the contact such that the user will be a contact for them.
     * Precondition: The given username is a valid contact.
     *
     * @param contactName the contacts name
     */
    @Override
    public void deleteContact(String contactName) {

        Contact contactEntity = user.getContact(contactName);

        // Fetch the contact and user
        Document contact = userRecords.find(eq("name", contactEntity.getName())).first();
        Document userDB = userRecords.find(eq("name", user.getName())).first();

        // Retrieving hash map of contacts to convo id
        Object contactToChatIDOfContactdb = contact.get("contactToChatID");
        Object contactToChatIDOfUserdb = userDB.get("contactToChatID");

        if (contactToChatIDOfContactdb instanceof Document contactsOfContactDocument
                && contactToChatIDOfUserdb instanceof Document contactsOfUserDocument) {

            // Convert the BSON Document back to a HashMap
            HashMap<String, Object> contactToChatIDOfContact = new HashMap<>(contactsOfContactDocument);
            HashMap<String, Object> contactToChatIDOfUser = new HashMap<>(contactsOfUserDocument);

            // Add conversation to database and fetch its ID
            removeConversation(conversationRecords.find(eq("_id",
                    contactToChatIDOfContact.get(user.getName()))).first());

            contactToChatIDOfContact.remove(user.getName());
            contactToChatIDOfUser.remove(contactEntity.getName());

            // Adds user to the map of contacts to the contact
            updateContactsDB(contactToChatIDOfContact, contact);

            // Adds the contact to the map of contacts of the user
            updateContactsDB(contactToChatIDOfUser, userDB);

            // Update to the in memory list of contacts
            user.getContacts().remove(contactEntity);
        }
    }

    /**
     * Creates a new entry in the conversation collection
     *
     * @return The id associated with the newly added entry
     */
    private ObjectId addConversation() {
        Document convoDoc = new Document()
                .append("messagesIDs", new ArrayList<>())
                .append("lastMessageTime", LocalDateTime.now());

        return conversationRecords.insertOne(convoDoc).getInsertedId().asObjectId().getValue();

    }

    /**
     * Delete a new entry in the conversation collection
     */
    public void removeConversation(Document convo) {

        conversationRecords.deleteOne(convo);

    }

    /**
     * Creates a new message entry in the database between the user and the given contact
     * Also updates the entities
     * Precondition: The given contact name is a valid contact for the user.
     *
     * @param contactName the contacts name
     * @param messageContent the content of the message to be sent
     */
    public void sendMessage(String contactName, String messageContent) {

        Document userDB = userRecords.find(eq("name", user.getName())).first();

        Object contactToChatID = userDB.get("contactToChatID");

        if (contactToChatID instanceof Document contactToChatIDDB) {

            // Converting column of contact to chat ID to map
            HashMap<String, Object> mapContactToChatID = new HashMap<>(contactToChatIDDB);

            // Adding message as an entry in the message collection
            // This will also return the ID associated with it
            ObjectId id = addMessage(messageContent);

            Document conversation = conversationRecords.find(eq("_id",
                    mapContactToChatID.get(contactName))).first();

            // List of message ids between the current contact and the user
            List<ObjectId> messagesIDs = conversation.getList("messagesIDs", ObjectId.class);

            // Adding the new message ID to the list of IDs
            messagesIDs.add(id);

            // Updates the conversation message IDs and last message time
            updateConversationDB(messagesIDs, conversation.getObjectId("_id"));

            // Updating the user entity
            Contact contact = user.getContact(contactName);

            // Updating the last message time to the current time
            contact.updateLastMessageTime();

            // Adding the new message to the end of the list
            contact.getMessages().add(messageFactory.create(messageContent, user.getName(), LocalDateTime.now()));
        }

    }

    /**
     * A helper function to update the conversation entry of the database between the user and contact
     *
     * @param messageIDs The list of message ids with the updated message
     * @param conversationId the conversation entry in the database to be updated
     */
    private void updateConversationDB(List<ObjectId> messageIDs, ObjectId conversationId) {

        Bson filter = Filters.eq("_id", conversationId);

        // Creates instructions to update the contact to chat id field
        Bson updates = Updates.combine(
                Updates.set("messagesIDs", messageIDs),
                Updates.set("lastMessageTime", LocalDateTime.now())
        );

        // Instructs the driver to insert a new document if none match the query
        UpdateOptions options = new UpdateOptions().upsert(true);

        try {
            // Updates the first document with the id, so it will only update one
            conversationRecords.updateOne(filter, updates, options);

        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    /**
     * Adds a new message entry into the message collection
     *
     * @param content the string content of the message
     * @return the ID of the new entry
     */
    private ObjectId addMessage(String content) {
        Document messageDoc = new Document()
                .append("content", content)
                .append("sender", user.getName())
                .append("messageTime", LocalDateTime.now());

        return messageRecords.insertOne(messageDoc).getInsertedId().asObjectId().getValue();

    }


    /**
     * This method should be called when the user would like to log in
     * If the username and password do not match a user, or the username is not valid null will be returned
     * Otherwise it will return the user with the given username.
     *
     * @param name     the username of the user
     * @param password the password of the user
     */
    @Override
    public void setUser(String name, String password) {

        Document dbUser = userRecords.find(eq("name", name)).first();

        if (dbUser == null || !dbUser.getString("password").equals(password)) {
            this.user = null;
        } else {

            // Fetch the contacts information, which includes the conversation
            ArrayList<Contact> contacts = new ArrayList<>();

            Object contactToChatIDdb = dbUser.get("contactToChatID");

            if (contactToChatIDdb instanceof Document contactsDocument) {

                // Convert the BSON Document back to a HashMap
                HashMap<String, Object> contactToChatID = new HashMap<>(contactsDocument);

                // Each iteration will configure one contact of the user contact list
                for (String contact : contactToChatID.keySet()) {

                    Document contactDoc = userRecords.find(eq("name", contact)).first();
                    Document convoDoc = conversationRecords.find(eq("_id", contactToChatID.get(contact))).first();

                    ArrayList<Message> messages = new ArrayList<>();

                    // List of message ids between the current contact and the user
                    List<ObjectId> messagesIDs = convoDoc.getList("messagesIDs", ObjectId.class);

                    // Iterates over all message Ids finding the message and converting it to the message entity
                    for (ObjectId messageID : messagesIDs) {
                        Document messageDoc = messageRecords.find(eq("_id", messageID)).first();
                        messages.add(messageFactory.create(messageDoc.getString("content"),
                                messageDoc.getString("sender"), messageDoc.getDate("messageTime").toInstant().
                                        atZone(ZoneId.systemDefault()).toLocalDateTime()));
                    }

                    LocalDateTime lastMessageTime = convoDoc.getDate("lastMessageTime").toInstant().
                            atZone(ZoneId.systemDefault()).toLocalDateTime();

                    // Combining all the efforts now to create a contact
                    contacts.add(contactFactory.create(contact, contactDoc.getString("preferredLanguage"),
                            lastMessageTime, messages));

                }
            } else {
                System.out.println("The 'contacts' field does not contain a HashMap.");
                this.user = null;
            }

            // Creates the user getting the string and date information
            this.user = userFactory.create(name, password,
                    dbUser.getString("preferredLanguage"), dbUser.getDate("creationTime").toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime(), contacts);
        }
    }

    /**
     * getter for user attribute
     * setUser should have been called once before this was called
     * @return the user attribute
     */
    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public void deleteUser(String name) {
        userRecords.deleteOne(Filters.eq("name", name));
    }
}