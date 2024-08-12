package user;
/**
 *
 * @author  Chang Li
 */
/**
 * Builder pattern for constructing User objects.
 */
public class UserBuilder {
    private int userId;
    private String name;
    private String email;
    private String password;
    private UserType userType;
    private String contactInfo;
    private String location;

    /**
     * Default constructor.
     */
    public UserBuilder() {
        // Set default values if needed
    }

    /**
     * Sets the user ID.
     * 
     * @param userId the user ID
     * @return the UserBuilder instance
     */
    public UserBuilder setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Sets the name.
     * 
     * @param name the name of the user
     * @return the UserBuilder instance
     */
    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the email.
     * 
     * @param email the email of the user
     * @return the UserBuilder instance
     */
    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Sets the password.
     * 
     * @param password the password of the user
     * @return the UserBuilder instance
     */
    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Sets the user type.
     * 
     * @param userType the type of user (e.g., consumer, retailer)
     * @return the UserBuilder instance
     */
    public UserBuilder setUserType(UserType userType) {
        this.userType = userType;
        return this;
    }

    /**
     * Sets the contact information.
     * 
     * @param contactInfo the contact information of the user
     * @return the UserBuilder instance
     */
    public UserBuilder setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
        return this;
    }

    /**
     * Sets the location.
     * 
     * @param location the location of the user
     * @return the UserBuilder instance
     */
    public UserBuilder setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Constructs and returns the User object.
     * 
     * @return the User object
     */
    public User build() {
        return new User(userId, name, email, password, userType, contactInfo, location);
    }
}
