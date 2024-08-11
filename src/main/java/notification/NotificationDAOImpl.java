package notification;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of Notification Data Access Object (DAO).
 * This class handles database operations related to notifications.
 */
public class NotificationDAOImpl implements NotificationDAO {
    private final DataSource dataSource;

    /**
     * Constructs a NotificationDAOImpl object with the specified DataSource.
     * @param dataSource The DataSource to be used for database operations.
     */
    public NotificationDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Creates a new notification in the database.
     * @param notification The Notification object to be created.
     */
    @Override
    public void create(Notification notification) {
        String sql = "INSERT INTO notifications (user_id, surplus_food_id, notification_message) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
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
        String sql = "SELECT * FROM notifications WHERE notification_id = ?";

        try (Connection connection = dataSource.getConnection();
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
    public void update(Notification notification) {
        String sql = "UPDATE notifications SET user_id = ?, surplus_food_id = ?, notification_message = ? WHERE notification_id = ?";

        try (Connection connection = dataSource.getConnection();
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
    public void delete(int notificationId) {
        String sql = "DELETE FROM notifications WHERE notification_id = ?";

        try (Connection connection = dataSource.getConnection();
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
                resultSet.getInt("notification_id"),
                resultSet.getInt("user_id"),
                resultSet.getInt("surplus_food_id"),
                resultSet.getString("notification_message")
        );
    }
}
