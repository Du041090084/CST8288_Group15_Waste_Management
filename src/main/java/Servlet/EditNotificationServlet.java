package Servlet;
/**
 *
 * @author Yuyang Du, Chang Li
 */
import inventory.InventoryItem;
import inventory.InventoryItemDAOImpl;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import notification.Notification;
import notification.NotificationDAO;
import notification.NotificationDAOImpl;

/**
 * Servlet implementation class EditNotificationsServlet
 * This servlet handles requests related to editing notification.
 */
@WebServlet("/EditNotificationsServlet")
public class EditNotificationServlet extends HttpServlet {
    private NotificationDAOImpl notificationDAO = new NotificationDAOImpl();

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the itemId parameter from the request
        int notificationId = Integer.parseInt(request.getParameter("notificationId"));

        // Retrieve the inventory item from the database using its itemId
        Notification notification = notificationDAO.read(notificationId);

        // Set the inventory item as an attribute in the request
        request.setAttribute("notification", notification);

        // Forward the request to the edit-item.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("createNotification.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String notificationIdStr = request.getParameter("notificationId");
    if (notificationIdStr == null || notificationIdStr.isEmpty()) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid notification ID");
        return;
    }

    int notificationId;
    try {
        notificationId = Integer.parseInt(notificationIdStr);
    } catch (NumberFormatException e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid notification ID format");
        return;
    }

        notificationDAO.deleteNotification(notificationId);
        response.sendRedirect("listNotifications.jsp");

}
}
