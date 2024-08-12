/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package subscription;
import java.util.List;

/**
 *
 * @author Yuyang Du
 */
public interface SubscriptionDAO {
    void create(subscription sub);

    /**
     * Retrieves a notification from the database by its ID.
     *
     * @param storeId
     * @return The notification object with the specified ID.
     */
    List<subscription> getAllSubscribers(int storeId);

    /**
     * Deletes a notification from the database by its ID.
     *
     * @param email
     * @param storeId
     */
    void deleteSubscription(String email, int storeId);
}
