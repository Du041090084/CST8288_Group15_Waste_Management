package notification;

/**
 *
 * @author Owner
 */
public interface NotificationDAO {
    /**
     * Creates a new notification in the database.
     *
     * @param notification The notification object to be created.
     */
    void create(Notification notification);

    /**
     * Retrieves a notification from the database by its ID.
     *
     * @param notificationId The ID of the notification to be retrieved.
     * @return The notification object with the specified ID.
     */
    Notification read(int notificationId);

    /**
     * Updates an existing notification in the database.
     *
     * @param notification The notification object with the updated information.
     */
    void update(Notification notification);

    /**
     * Deletes a notification from the database by its ID.
     *
     * @param notificationId The ID of the notification to be deleted.
     */
    void delete(int notificationId);
}
