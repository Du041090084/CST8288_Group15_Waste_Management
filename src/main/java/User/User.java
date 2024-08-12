package user;


/**
 * Represents a user entity.
 */
public class User {
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
    public User() {
    }

    /**
     * Constructs a User with the specified attributes.
     * 
     * @param userId the user ID
     * @param name the name of the user
     * @param email the email of the user
     * @param password the password of the user
     * @param userType the type of user (e.g., consumer, retailer)
     * @param contactInfo the contact information of the user
     * @param location the location of the user
     */
    public User(int userId, String name, String email, String password, UserType userType, String contactInfo, String location) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.contactInfo = contactInfo;
        this.location = location;
    }

    // Getters and setters

    /**
     *
     * @return
     */

    public int getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     *
     * @param userType
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     *
     * @return
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     *
     * @param contactInfo
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
