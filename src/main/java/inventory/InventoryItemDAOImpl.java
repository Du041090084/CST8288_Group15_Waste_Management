package inventory;
/**
 *
 * @author Yuyang Du, Chang Li
 */
import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the InventoryItemDAO interface for managing inventory items.
 * This class provides methods to perform CRUD operations on inventory items in the database.
 * It also includes additional methods to retrieve specific types of inventory items.
 * <p>
 * This implementation uses JDBC for database interaction.
 * </p>
 * 
 */
public class InventoryItemDAOImpl implements InventoryItemDAO {

    /**
     * Adds an inventory item to the database.
     * 
     * @param item The inventory item to add.
     */
    @Override
    public void addInventoryItem(InventoryItem item) {
        String sql = "INSERT INTO inventoryItem (itemName, itemDescription, quantity, expirationDate, forDonation, surplus, storeId) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getItemName());
            statement.setString(2, item.getItemDescription());
            statement.setInt(3, item.getQuantity());
            statement.setDate(4, item.getExpirationDate());
            statement.setBoolean(5, item.isForDonation());
            statement.setBoolean(6, item.isSurplus());
            statement.setInt(7,item.getStoreId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<InventoryItem> getAllInventoryItems() {
        List<InventoryItem> inventoryItems = new ArrayList<>();
        String sql = "SELECT * FROM inventoryItem";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                InventoryItem item = new InventoryItem();
                item.setItemId(resultSet.getInt("itemId"));
                item.setItemName(resultSet.getString("itemName"));
                item.setItemDescription(resultSet.getString("itemDescription"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setExpirationDate(resultSet.getDate("expirationDate"));
                item.setForDonation(resultSet.getBoolean("forDonation"));
                item.setSurplus(resultSet.getBoolean("surplus"));
                item.setStoreId(resultSet.getInt("storeId"));
                inventoryItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventoryItems;
    }

    @Override
    public void updateInventoryItem(InventoryItem item) {
        String sql = "UPDATE inventoryItem SET itemName = ?, itemDescription = ?, quantity = ?, expirationDate = ?, " +
                     "forDonation = ?, surplus = ?, storeId = ? WHERE itemId = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getItemName());
            statement.setString(2, item.getItemDescription());
            statement.setInt(3, item.getQuantity());
            statement.setDate(4, item.getExpirationDate());
            statement.setBoolean(5, item.isForDonation());
            statement.setBoolean(6, item.isSurplus());
            statement.setInt(7, item.getStoreId());
            statement.setInt(8,item.getItemId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteInventoryItem(int itemId) {
        String sql = "DELETE FROM inventoryItem WHERE itemId = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, itemId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retrieves all inventory items that are not marked for donation.
     * 
     * @return A list of inventory items not marked for donation.
     */
    public List<InventoryItem> getNonDonationInventoryItems() {
        List<InventoryItem> inventoryItems = new ArrayList<>();
        String sql = "SELECT * FROM inventoryItem WHERE forDonation = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, false); // Select non-donation items
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    InventoryItem item = new InventoryItem();
                    item.setItemId(resultSet.getInt("itemId"));
                    item.setItemName(resultSet.getString("itemName"));
                    item.setItemDescription(resultSet.getString("itemDescription"));
                    item.setQuantity(resultSet.getInt("quantity"));
                    item.setExpirationDate(resultSet.getDate("expirationDate"));
                    item.setForDonation(resultSet.getBoolean("forDonation"));
                    item.setSurplus(resultSet.getBoolean("surplus"));
                    item.setStoreId(resultSet.getInt("storeId"));
                    inventoryItems.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventoryItems;
    }
    
    /**
     * Retrieves all inventory items that are marked for donation.
     * 
     * @return A list of inventory items marked for donation.
     */
    public List<InventoryItem> getDonationInventoryItems() {
        List<InventoryItem> donationItems = new ArrayList<>();
        String sql = "SELECT * FROM inventoryItem WHERE forDonation = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, true);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    InventoryItem item = new InventoryItem();
                    item.setItemId(resultSet.getInt("itemId"));
                    item.setItemName(resultSet.getString("itemName"));
                    item.setItemDescription(resultSet.getString("itemDescription"));
                    item.setQuantity(resultSet.getInt("quantity"));
                    item.setExpirationDate(resultSet.getDate("expirationDate"));
                    item.setForDonation(resultSet.getBoolean("forDonation"));
                    item.setSurplus(resultSet.getBoolean("surplus"));
                    item.setStoreId(resultSet.getInt("storeId"));
                    donationItems.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donationItems;
    }

    /**
     * Retrieves an inventory item by its ID.
     * 
     * @param itemId The ID of the inventory item to retrieve.
     * @return The inventory item with the specified ID, or null if not found.
     */
    public InventoryItem getInventoryItemById(int itemId) {
        String sql = "SELECT * FROM inventoryItem WHERE itemId = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, itemId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    InventoryItem item = new InventoryItem();
                    item.setItemId(resultSet.getInt("itemId"));
                    item.setItemName(resultSet.getString("itemName"));
                    item.setItemDescription(resultSet.getString("itemDescription"));
                    item.setQuantity(resultSet.getInt("quantity"));
                    item.setExpirationDate(resultSet.getDate("expirationDate"));
                    item.setForDonation(resultSet.getBoolean("forDonation"));
                    item.setSurplus(resultSet.getBoolean("surplus"));
                    item.setStoreId(resultSet.getInt("storeId"));
                    return item;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no item found with the given ID
    }
    public List<InventoryItem> getStoreInventoryItems(int storeId) {
        List<InventoryItem> donationItems = new ArrayList<>();
        String sql = "SELECT * FROM inventoryItem WHERE forDonation = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, storeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    InventoryItem item = new InventoryItem();
                    item.setItemId(resultSet.getInt("itemId"));
                    item.setItemName(resultSet.getString("itemName"));
                    item.setItemDescription(resultSet.getString("itemDescription"));
                    item.setQuantity(resultSet.getInt("quantity"));
                    item.setExpirationDate(resultSet.getDate("expirationDate"));
                    item.setForDonation(resultSet.getBoolean("forDonation"));
                    item.setSurplus(resultSet.getBoolean("surplus"));
                    item.setStoreId(resultSet.getInt("storeId"));
                    donationItems.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donationItems;
    }
}
