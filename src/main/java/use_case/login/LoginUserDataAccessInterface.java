package use_case.login;

import entity.User;

public interface LoginUserDataAccessInterface {

    User getUser(String username, String password);
}
