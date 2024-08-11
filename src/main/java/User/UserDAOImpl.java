package user;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;


/**
 * Implementation of User Data Access Object (DAO).
 * This class handles database operations related to users.
 */
public class UserDAOImpl implements UserDAO {

    // Removed DataSource as we will use DBConnection

    /**
     * Constructs a UserDAOImpl object with default DataSource.
     */
    public UserDAOImpl() {
        // No need to initialize DataSource here
    }

    /**
     * Constructs a UserDAOImpl object with custom DataSource.
     * @param dataSource The DataSource object to be used.
     */
    public UserDAOImpl(DataSource dataSource) {
        // No need to initialize DataSource here
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO user (name, email, password, userType, contactInfo, location) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUserType().getDatabaseValue());
            statement.setString(5, user.getContactInfo());
            statement.setString(6, user.getLocation());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating user", e);
        }
    }

    @Override
    public User read(int userId) {
        String sql = "SELECT * FROM user WHERE userId = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapUser(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE user SET name = ?, email = ?, password = ?, userType = ?, contactInfo = ?, location = ?" +
                "WHERE userId = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUserType().getDatabaseValue());
            statement.setString(5, user.getContactInfo());
            statement.setString(6, user.getLocation());

            statement.setInt(7, user.getUserId()); // Fixed index from 8 to 7
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int userId) {
        String sql = "DELETE FROM user WHERE userId = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapUser(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public UserType getUserType(String email) {
        UserType userType = null;
        String sql = "SELECT userType FROM user WHERE email = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String userTypeString = resultSet.getString("userType");
                    userType = UserType.valueOf(userTypeString);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return userType;
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        String sql = "SELECT COUNT(*) FROM user WHERE email = ? AND password = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to map ResultSet to User object
    private User mapUser(ResultSet resultSet) throws SQLException {
        return new UserBuilder()
                .setUserId(resultSet.getInt("userId"))
                .setName(resultSet.getString("name"))
                .setEmail(resultSet.getString("email"))
                .setPassword(resultSet.getString("password"))
                .setUserType(UserType.valueOf(resultSet.getString("userType")))
                .setContactInfo(resultSet.getString("contactInfo"))
                .setLocation(resultSet.getString("location"))
                .build();
    }
}
