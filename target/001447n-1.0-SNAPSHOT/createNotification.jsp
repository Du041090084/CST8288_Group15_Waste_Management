<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Notification</title>
</head>
<body>
    <h2 style="text-align:center;">Create New Notification</h2>

    <form action="RedirectNotification" method="get">
        <input type="hidden" name="action" value="create">

        <label for="userId">User ID:</label>
        <input type="text" id="userId" name="userId" required>

        <label for="surplusFoodId">Surplus Food ID:</label>
        <input type="text" id="surplusFoodId" name="surplusFoodId" required>

        <label for="notificationMessage">Notification Message:</label>
        <input type="text" id="notificationMessage" name="notificationMessage" required>

        <input type="submit" value="Create Notification">

        <% 
            // Display any error messages
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
            <div class="error"><%= errorMessage %></div>
        <% 
            }
        %>
    </form>

    <div style="text-align:center;">
        <a href="listNotifications.jsp">Back to Notification List</a>
    </div>
</body>
</html>
