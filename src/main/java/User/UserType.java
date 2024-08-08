package user;

/**
 * Enum representing different types of users.
 */
public enum UserType {

    
    RETAILER("RETAILER"),

    /**
     *
     */
    CONSUMER("CONSUMER"),

    /**
     *
     */
    CHARITABLE_ORGANIZATION("CHARITABLE_ORGANIZATION");

    private final String databaseValue;

    /**
     * Constructs a UserType enum with a corresponding database value.
     * @param databaseValue The value to be stored in the database.
     */
    UserType(String databaseValue) {
        this.databaseValue = databaseValue;
    }

    /**
     * Gets the database value of the UserType.
     * @return The database value.
     */
    public String getDatabaseValue() {
        return databaseValue;
    }
}
