package interface_adapter;

/**
 * RuntimeException thrown when user creation fails.
 */
public class UserCreationFailed extends RuntimeException {

    /**
     * Constructs a new UserCreationFailed exception with the specified error message.
     *
     * @param error The error message indicating the reason for user creation failure.
     */
    public UserCreationFailed(String error) {
        super(error);
    }
}