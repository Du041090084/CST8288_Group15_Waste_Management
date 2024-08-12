package notification;
/**
 *
 * @author Yuyang Du, Chang Li
 */
import database.DBConnection;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Notification Data Access Object (DAO).
 * This class handles database operations related to notifications.
 */
public class NotificationDAOImpl implements NotificationDAO {
    
    public NotificationDAOImpl() {
        // No need to initialize DataSource here
    }

    /**
     * Constructs a UserDAOImpl object with custom DataSource.
     * @param dataSource The DataSource object to be used.
     */
    public NotificationDAOImpl(DataSource dataSource) {
        // No need to initialize DataSource here
    }

    /**
     * Creates a new notification in the database.
     * @param notification The Notification object to be created.
     */
    @Override
    public void create(Notification notification) {
        String sql = "INSERT INTO notifications (userId, surplusFoodId, notificationMessage) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, notification.getUserId());
            statement.setInt(2, notification.getSurplusFoodId());
            statement.setString(3, notification.getNotificationMessage());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a notification from the database based on notification ID.
     * @param notificationId The ID of the notification to be retrieved.
     * @return The Notification object if found, otherwise null.
     */
    @Override
    public Notification read(int notificationId) {
        String sql = "SELECT * FROM notifications WHERE notificationId = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, notificationId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapNotification(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * Updates an existing notification in the database.
     * @param notification The Notification object to be updated.
     */
    @Override
    public void updateNotification(Notification notification) {
        String sql = "UPDATE notifications SET userId = ?, surplusFoodId = ?, notificationMessage = ? WHERE notificationId = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, notification.getUserId());
            statement.setInt(2, notification.getSurplusFoodId());
            statement.setString(3, notification.getNotificationMessage());
            statement.setInt(4, notification.getNotificationId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a notification from the database based on notification ID.
     * @param notificationId The ID of the notification to be deleted.
     */
    @Override
    public void deleteNotification(int notificationId) {
        String sql = "DELETE FROM notifications WHERE notificationId = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, notificationId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to map ResultSet to Notification object.
     * @param resultSet The ResultSet containing notification data.
     * @return The Notification object mapped from ResultSet.
     * @throws SQLException If a SQL exception occurs.
     */
    private Notification mapNotification(ResultSet resultSet) throws SQLException {
        return new Notification(
                resultSet.getInt("notificationId"),
                resultSet.getInt("userId"),
                resultSet.getInt("surplusFoodId"),
                resultSet.getString("notificationMessage")
        );
    }
    
    /**
     * Retrieves all inventory items that are marked for donation.
     * 
     * @param userId
     * @return A list of inventory items marked for donation.
     */
    public List<Notification> getNotificationsById(int userId) {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM notifications WHERE userId = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Notification notification = new Notification(resultSet.getInt("notificationId"),resultSet.getInt("userId"), resultSet.getInt("surplusFoodId"),resultSet.getString("notificationMessage"));
                    notifications.add(notification);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notifications;
    }
    public List<Notification> getAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM notifications";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Notification notification = new Notification(resultSet.getInt("notificationId"),resultSet.getInt("userId"),resultSet.getInt("surplusFoodId"),resultSet.getString("notificationMessage"));
                    notifications.add(notification);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notifications;
    }
}
