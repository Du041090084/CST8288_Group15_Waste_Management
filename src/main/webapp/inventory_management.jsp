<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory Management</title>
</head>
<body>
    <h1>Inventory Management</h1>

    <!-- Form for adding new inventory item -->
    <form action="inventory" method="post">
        <h2>Add Inventory Item</h2>
        <label for="itemName">Item Name:</label>
        <input type="text" id="itemName" name="itemName" required><br>
        <label for="itemDescription">Description:</label>
        <input type="text" id="itemDescription" name="itemDescription"><br>
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required><br>
        <label for="expirationDate">Expiration Date:</label>
        <input type="date" id="expirationDate" name="expirationDate" required><br>
        <input type="hidden" name="action" value="add">
        <button type="submit">Add Inventory</button>
    </form>

    <!-- Form for updating an existing inventory item -->
    <form action="inventory" method="post">
        <h2>Update Inventory Item</h2>
        <label for="updateItemId">Item ID:</label>
        <input type="number" id="updateItemId" name="updateItemId" required><br>
        <label for="itemName">Item Name:</label>
        <input type="text" id="itemName" name="itemName" required><br>
        <label for="itemDescription">Description:</label>
        <input type="text" id="itemDescription" name="itemDescription"><br>
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required><br>
        <label for="expirationDate">Expiration Date:</label>
        <input type="date" id="expirationDate" name="expirationDate" required><br>
        <input type="hidden" name="action" value="add">
        <input type="hidden" name="action" value="update">
        <button type="submit">Update Inventory</button>
    </form>

    <!-- Form for deleting an existing inventory item -->
    <form action="inventory" method="post">
        <h2>Delete Inventory Item</h2>
        <label for="deleteItemId">Item ID:</label>
        <input type="number" id="deleteItemId" name="deleteItemId" required><br>
        <input type="hidden" name="action" value="delete">
        <button type="submit">Delete Inventory</button>
    </form>
</body>
</html>
