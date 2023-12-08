package services.conversation.sync_conversation_view;

import data_access.MongoDataAccessObject;
import entities.CommonUserFactory;
import org.junit.jupiter.api.Test;

class SyncConversationInteractorTest {

    @Test
    void execute() {
        SyncConversationInputData inputData = new SyncConversationInputData("Andy");
        MongoDataAccessObject mongoDataAccessInterface = new MongoDataAccessObject(System.getenv("MONGO_PASSWORD"),
                new CommonUserFactory());
        mongoDataAccessInterface.setUser("jchris", "o");

        SyncConversationOutputBoundary conversationSyncPresenter = new SyncConversationOutputBoundary() {
            @Override
            public void prepareSuccessView(SyncConversationOutputData conversationSyncOutputData) {

            }

            @Override
            public void prepareFailView(String error) {

            }
        };

        SyncConversationInteractor interactor = new SyncConversationInteractor(mongoDataAccessInterface,
                conversationSyncPresenter);
        interactor.execute(inputData);
    }
}