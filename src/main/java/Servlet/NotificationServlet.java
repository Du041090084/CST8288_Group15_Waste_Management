package Servlet;
/**
 *
 * @author Yuyang Du
 */

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


/**
 * Servlet implementation class NotificationServlet
 * This servlet handles requests related to notifications.
 */
@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {

    /**private final NotificationDAOImpl notificationDAO = new NotificationDAOImpl();

    /**
    * 
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");

    if ("create".equals(action)) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int surplusFoodId = Integer.parseInt(request.getParameter("surplusFoodId"));
        String notificationMessage = request.getParameter("notificationMessage");

        Notification notification = new Notification(0, userId, notificationMessage);
        NotificationDAO notificationDAO = new NotificationDAOImpl(dataSource);
        
        try {
            notificationDAO.create(notification);
            response.sendRedirect("listNotifications.jsp"); // Redirect to the list of notifications after creation
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Failed to create notification. Please try again.");
            request.getRequestDispatcher("createNotification.jsp").forward(request, response);
        }
    }
}*/
}