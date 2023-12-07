package services.conversation.sync_conversation_view;

import data_access.MongoDataAccessObject;
import entities.CommonUserFactory;
import org.junit.jupiter.api.Test;
import services.contact.add_contact.AddContactInputData;
import services.contact.add_contact.AddContactInteractor;
import services.contact.add_contact.AddContactOutputBoundary;

import static org.junit.jupiter.api.Assertions.*;

class ConversationSyncInteractorTest {

    @Test
    void execute() {
        ConversationSyncInputData inputData = new ConversationSyncInputData("Andy");
        MongoDataAccessObject mongoDataAccessInterface = new MongoDataAccessObject(
                System.getenv("MONGO_PASSWORD"),
                new CommonUserFactory()
        );
        mongoDataAccessInterface.setUser("jchris", "o");

        ConversationSyncOutputBoundary conversationSyncPresenter = new ConversationSyncOutputBoundary() {
            @Override
            public void prepareSuccessView(ConversationSyncOutputData conversationSyncOutputData) {

            }

            @Override
            public void prepareFailView(String error) {

            }
        };

        ConversationSyncInteractor interactor =
                new ConversationSyncInteractor(mongoDataAccessInterface, conversationSyncPresenter);
        interactor.execute(inputData);
    }
}