/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import subscription.Emailer;
import notification.Notification;
import notification.NotificationDAOImpl;

/**
 *
 * @author li162
 */
@WebServlet(name = "RedirectNotification", urlPatterns = {"/RedirectNotification"})
public class RedirectNotification extends HttpServlet {
    private final NotificationDAOImpl notificationDAO = new  NotificationDAOImpl();
    private final Emailer email = new Emailer();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String UserType = (String) request.getSession().getAttribute("userType");
        int userId =  (int) request.getSession().getAttribute("userId");
        switch (UserType){
            case "RETAILER":
                // Fetch donation inventory items from the database
                List<Notification> Notifications = notificationDAO.getAllNotifications();

                // Set donationInventoryItems as an attribute in the request
                request.setAttribute("notifications", Notifications);

                // Forward the request to charitable_organization.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("listNotifications.jsp");
                dispatcher.forward(request, response);
                response.sendRedirect("listNotifications.jsp");
                break;
            case "CONSUMER":
                
                Notifications = notificationDAO.getNotificationsById(userId);

                // Set donationInventoryItems as an attribute in the request
                request.setAttribute("notifications", Notifications);

                // Forward the request to charitable_organization.jsp
                dispatcher = request.getRequestDispatcher("listNotifications_CONSUMER.jsp");
                dispatcher.forward(request, response);
                response.sendRedirect("listNotifications_CONSUMER.jsp");
                break;
            case "CHARITY":
                Notifications = notificationDAO.getNotificationsById(userId);

                // Set donationInventoryItems as an attribute in the request
                request.setAttribute("notifications", Notifications);

                // Forward the request to charitable_organization.jsp
                dispatcher = request.getRequestDispatcher("listNotifications_CONSUMER.jsp");
                dispatcher.forward(request, response);
                response.sendRedirect("listNotifications_CONSUMER.jsp");
                break;
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Notification newNotifiation = new Notification(1,parseInt(request.getParameter("userId")),parseInt(request.getParameter("surplusFoodId")),request.getParameter("notificationMessage"));
        notificationDAO.create(newNotifiation);
        String SenderEmail = (String) request.getSession().getAttribute("SenderEmail");
        List<Notification> Notifications = notificationDAO.getAllNotifications();

                // Set donationInventoryItems as an attribute in the request
                request.setAttribute("notifications", Notifications);

                // Forward the request to charitable_organization.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("listNotifications.jsp");
                dispatcher.forward(request, response);
        response.sendRedirect("listNotifications.jsp");

    }

}
