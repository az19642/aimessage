package use_case.signup;

/**
 * Represents the output data for the signup use case, providing information about the result of the operation.
 */
public class SignupOutputData {

    private final String username;
    private String creationTime;

    private boolean useCaseFailed;

    public SignupOutputData(String username, String creationTime, boolean useCaseFailed) {
        this.username = username;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Gets the username associated with the signup.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the timestamp indicating when the signup was completed.
     *
     * @return The creation time.
     */
    public String getCreationTime() {
        return creationTime;
    }

    /**
     * Sets the creation time for the signup.
     *
     * @param creationTime The timestamp indicating when the signup was completed.
     */
    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

}