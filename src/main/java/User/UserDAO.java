package user;
/**
 *
 * @author  Chang Li
 */
/**
 * Interface for performing CRUD operations on user entities.
 */
public interface UserDAO {

    /**
     * Creates a new user in the data source.
     * 
     * @param user the user to create
     */
    void create(User user);

    /**
     * Retrieves a user from the data source by their user ID.
     * 
     * @param userId the ID of the user to retrieve
     * @return the retrieved user, or null if not found
     */
    User read(int userId);

    /**
     * Updates an existing user in the data source.
     * 
     * @param user the user to update
     */
    void update(User user);

    /**
     * Deletes a user from the data source by their user ID.
     * 
     * @param userId the ID of the user to delete
     */
    void delete(int userId);

    /**
     * Retrieves a user from the data source by their email address.
     * 
     * @param email the email address of the user to retrieve
     * @return the retrieved user, or null if not found
     */
    User getUserByEmail(String email);

    /**
     * Retrieves the type of the user from the data source by their username.
     * 
     * @param username the username of the user to retrieve the type for
     * @return the user type, or null if not found
     */
    UserType getUserType(String username);

    /**
     * Authenticates a user by their email address and password.
     * 
     * @param email the email address of the user
     * @param password the password of the user
     * @return true if the user is authenticated, false otherwise
     */
    boolean authenticateUser(String email, String password);
}
