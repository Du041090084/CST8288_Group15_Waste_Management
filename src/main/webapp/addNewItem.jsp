<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Item</title>
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
    <h1>Add New Item</h1>
    <form action="AddNewItemServlet" method="post">
        <div class="form-group">
            <label for="itemName">Item Name:</label>
            <input type="text" id="itemName" name="itemName" required>
        </div>

        <div class="form-group">
            <label for="itemDescription">Item Description:</label>
            <input type="text" id="itemDescription" name="itemDescription">
        </div>

        <div class="form-group">
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" required>
        </div>

        <div class="form-group">
            <label for="expirationDate">Expiration Date:</label>
            <input type="date" id="expirationDate" name="expirationDate">
        </div>

        <div class="form-group">
            <label for="forDonation">For Donation:</label>
            <input type="checkbox" id="forDonation" name="forDonation" value="true">
        </div>

        <div class="form-group">
            <label for="surplus">Surplus:</label>
            <input type="checkbox" id="surplus" name="surplus" value="true">
        </div>

        <button type="submit">Add Item</button>
    </form>
        </div>
     <footer>
        <p>&copy; 2024 Platform Name. All rights reserved.</p>
    </footer>
</body>
</html>

