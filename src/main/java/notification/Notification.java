package notification;

/**
 * Represents a notification.
 */
public class Notification {
    private int notificationId;
    private int userId;
    private int surplusFoodId;
    private String notificationMessage;

    /**
     * Constructs a Notification object with specified parameters.
     * @param notificationId The ID of the notification.
     * @param userId The ID of the user to whom the notification is sent.
     * @param surplusFoodId The ID of the surplus food associated with the notification.
     * @param notificationMessage The message content of the notification.
     */
    public Notification(int notificationId, int userId, int surplusFoodId, String notificationMessage) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.surplusFoodId = surplusFoodId;
        this.notificationMessage = notificationMessage;
    }

    // Getters and setters

    /**
     * Gets the ID of the notification.
     * @return The notification ID.
     */
    public int getNotificationId() {
        return notificationId;
    }

    /**
     * Sets the ID of the notification.
     * @param notificationId The notification ID to set.
     */
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    /**
     * Gets the ID of the user to whom the notification is sent.
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user to whom the notification is sent.
     * @param userId The user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the ID of the surplus food associated with the notification.
     * @return The surplus food ID.
     */
    public int getSurplusFoodId() {
        return surplusFoodId;
    }

    /**
     * Sets the ID of the surplus food associated with the notification.
     * @param surplusFoodId The surplus food ID to set.
     */
    public void setSurplusFoodId(int surplusFoodId) {
        this.surplusFoodId = surplusFoodId;
    }

    /**
     * Gets the message content of the notification.
     * @return The notification message.
     */
    public String getNotificationMessage() {
        return notificationMessage;
    }

    /**
     * Sets the message content of the notification.
     * @param notificationMessage The notification message to set.
     */
    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }
}
