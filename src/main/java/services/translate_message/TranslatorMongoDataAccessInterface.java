package services.translate_message;

import entities.User;

public interface TranslatorMongoDataAccessInterface {

    /**
     * getter for user attribute
     * setUser should have been called once before this was called
     *
     * @return the user attribute
     */
    public User getUser();

}
