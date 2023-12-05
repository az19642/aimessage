package data_access;

import entity.User;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {

    private final Map<String, User> accounts = new HashMap<>();
    private User user = null;

    /**
     * @param identifier the user's username
     * @return whether the user exists
     */
    @Override
    public boolean userExists(String identifier) {
        return accounts.containsKey(identifier);
    }

    /**
     * @param user the data to save
     */
    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
    }

    /**
     * @param username the username of the user
     * @param password the password of the user
     * @return the user based on the given input
     */
    @Override
    public void setUser(String username, String password) {
        this.user = accounts.get(username);
    }

    @Override
    public User getUser(){
        return this.user;
    }
}