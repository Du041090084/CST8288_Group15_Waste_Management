<%@page import="inventory.InventoryItem"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory Management for Charitable Organization</title>
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
    <h1>Inventory Management for Charitable Organization</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Item Name</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>Expiration Date</th>
                <th>Days Until Expiration</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<InventoryItem> inventoryItems = (List<InventoryItem>) request.getAttribute("inventoryItems");
            for (InventoryItem item : inventoryItems) { 
            %>
                <tr>
                    <td><%= item.getItemName() %></td>
                    <td><%= item.getItemDescription() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td><%= item.getExpirationDate() %></td>
                    <td><%= item.getDays_Until_Expiration() %></td>
                    <td>
                    <form action="ClaimFoodServlet" method="post">
                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                    <button type="submit">Claim</button>
                    </form>
                    </td>
                    <td>
                    <form action="SubscriptionServlet" method="get">
                    <input type="hidden" name="storeId" value="<%= item.getStoreId() %>">
                    <button type="submit">Subscribe to this store!</button>
                    </form> 
                    </td>
                    
                </tr>
            <% } %>
        </tbody>
    </table>
        </div>
         <footer>
        <p>&copy; 2024 Platform Name. All rights reserved.</p>
    </footer>
</body>
</html>
