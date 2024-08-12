<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Inventory Item</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <header>
        <h1>Food Waste Reduction Platform</h1>
        <nav>
            <a href="index.jsp">Home</a>
            <a href="login.jsp">Login</a>
            <a href="register.jsp">Register</a>
            <a href="LogoutServlet">Logout</a>
        </nav>
    </header>
    <div class="content-container">
        <h1>Edit Inventory Item</h1>
        <form action="EditItemServlet" method="post">
            <input type="hidden" name="itemId" value="${item.itemId}">
            <label for="itemName">Item Name:</label>
            <input type="text" id="itemName" name="itemName" value="${item.itemName}" required><br>
            <label for="itemDescription">Description:</label>
            <input type="text" id="itemDescription" name="itemDescription" value="${item.itemDescription}"><br>
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" value="${item.quantity}" required><br>
            <label for="expirationDate">Expiration Date:</label>
            <input type="date" id="expirationDate" name="expirationDate" value="${item.expirationDate}" required><br>
            <label for="forDonation">For Donation:</label>
            <input type="checkbox" id="forDonation" name="forDonation" ${item.forDonation ? 'checked' : ''}><br>
            <label for="surplus">Surplus:</label>
            <input type="checkbox" id="surplus" name="surplus" ${item.surplus ? 'checked' : ''}><br>
            <button type="submit">Save</button>
        </form>

        <!-- Delete Button Form -->
        <form action="EditItemServlet" method="post">
    <input type="hidden" name="itemId" value="${item.itemId}">
    <button type="submit" name="delete" value="true" class="delete-button">Delete</button>
</form>

    </div>
    <footer>
        <p>&copy; 2024 Platform Name. All rights reserved.</p>
    </footer>
</body>
</html>
