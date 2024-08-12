package inventory;

import java.sql.Date;

/**
 * Represents an item in the inventory.
 */
public class InventoryItem {
    private int itemId;
    private String itemName;
    private String itemDescription;
    private int quantity;
    private Date expirationDate;
    private int days_until_expiration;
    private boolean forDonation;
    private boolean surplus;

    /**
     * Constructs an InventoryItem object with specified parameters.
     * @param itemId The ID of the item.
     * @param itemName The name of the item.
     * @param itemDescription The description of the item.
     * @param quantity The quantity of the item.
     * @param expirationDate The expiration date of the item.
     * @param forDonation Indicates if the item is for donation.
     * @param surplus Indicates if the item is surplus.
     */
    public InventoryItem(int itemId, String itemName, String itemDescription, int quantity, Date expirationDate, boolean forDonation, boolean surplus) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        long difference = expirationDate.getTime() - System.currentTimeMillis();
        this.days_until_expiration = (int) Math.floor(difference/86400000);
        this.forDonation = forDonation;
        this.surplus = surplus;
    }

    /**
     * Constructs an empty InventoryItem object.
     */
    public InventoryItem() {
    }

    // Getters and setters

    /**
     * Gets the ID of the item.
     * @return The item ID.
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Sets the ID of the item.
     * @param itemId The item ID to set.
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * Gets the name of the item.
     * @return The item name.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the name of the item.
     * @param itemName The item name to set.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the description of the item.
     * @return The item description.
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Sets the description of the item.
     * @param itemDescription The item description to set.
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * Gets the quantity of the item.
     * @return The item quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item.
     * @param quantity The item quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the expiration date of the item.
     * @return The item expiration date.
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the item.
     * Whenever expiration date is set/updated, days until expiration is automatically set
     * @param expirationDate The item expiration date to set.
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
        long difference = (long) (Math.floor(expirationDate.getTime()/86400000) - Math.floor(System.currentTimeMillis()/86400000));
        this.days_until_expiration = (int) difference;
    }

    /**
     * Gets how many days until item expires from current date
     * calculation is done when function is called to ensure accurate value every build
     * @return how many days until the item expires
     */
    public int getDays_Until_Expiration(){
        long difference = (long) (Math.floor(expirationDate.getTime()/86400000) - Math.floor(System.currentTimeMillis()/86400000));
        this.days_until_expiration = (int) difference;
        return this.days_until_expiration;
    }
    /**
     * Checks if the item is for donation.
     * @return True if the item is for donation, otherwise false.
     */
    public boolean isForDonation() {
        return forDonation;
    }

    /**
     * Sets the donation status of the item.
     * @param forDonation The donation status to set.
     */
    public void setForDonation(boolean forDonation) {
        this.forDonation = forDonation;
    }

    /**
     * Checks if the item is surplus.
     * @return True if the item is surplus, otherwise false.
     */
    public boolean isSurplus() {
        return surplus;
    }

    /**
     * Sets the surplus status of the item.
     * @param surplus The surplus status to set.
     */
    public void setSurplus(boolean surplus) {
        this.surplus = surplus;
    }
}
