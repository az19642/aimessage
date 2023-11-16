package use_case.signup;

import entity.User;

public interface SignupUserDataAccessInterface {
    boolean userExists(String identifier);

    void save(User user);
}
