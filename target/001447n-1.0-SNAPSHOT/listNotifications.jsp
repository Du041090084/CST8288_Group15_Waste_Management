<%@ page import="notification.Notification" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Notification List</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h2 style="text-align:center;">Notification List</h2>

    <table>
        <thead>
            <tr>
                <th>Notification ID</th>
                <th>User ID</th>
                <th>Surplus Food ID</th>
                <th>Message</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                // Retrieve the list of notifications from the servlet context or session
                List<Notification> notifications = (List<Notification>) request.getAttribute("notifications");

                if (notifications != null && !notifications.isEmpty()) {
                    for (Notification notification : notifications) {
            %>
                        <tr>
                            <td><%= notification.getNotificationId() %></td>
                            <td><%= notification.getUserId() %></td>
                            <td><%= notification.getSurplusFoodId() %></td>
                            <td><%= notification.getNotificationMessage() %></td>
                            <td>
                                <form action="EditNotificationsServlet" method="post">
        <input type="hidden" name="notificationId" value="<%= notification.getNotificationId() %>">
        <button type="submit">Delete</button>
                      </form>
                            </td>
                        </tr>
            <%
                    }
                } else {
            %>
                    <tr>
                        <td colspan="5" style="text-align:center;">No notifications found.</td>
                    </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <div style="text-align:center;">
        <a href="createNotification.jsp">Create New Notification</a>
    </div>
</body>
</html>
