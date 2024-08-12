package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.User;
import user.UserDAOImpl;
import user.UserType;

/**
 * Servlet implementation class LoginServlet
 * This servlet handles user login functionality.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final UserDAOImpl userDAO = new UserDAOImpl();

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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Authenticate user
        if (userDAO.authenticateUser(email, password)) {
            // Retrieve user type
            UserType userType = userDAO.getUserType(email);
            User relevant = userDAO.getUserByEmail(email);
            request.getSession().setAttribute("userId",relevant.getUserId());
            request.getSession().setAttribute("SenderEmail",email);
            // Redirect user based on user type
            if (userType != null) {
                switch (userType) {
                    case CONSUMER:
                        request.getSession().setAttribute("userType","CONSUMER");
                        response.sendRedirect("ConsumerServlet");
                       
                        break;
                    case RETAILER:
                        request.getSession().setAttribute("userType","RETAILER");
                        response.sendRedirect("RetailerServlet");
                        
                        break;
                    case CHARITABLE_ORGANIZATION:
                        request.getSession().setAttribute("userType","CHARITY");
                        response.sendRedirect("CharitableOrganizationServlet");
                        
                        break;
                    default:
                        // Handle other cases or errors
                        break;
                }
            } else {
                // Handle case when user type is null
                // For example, if user not found or other error occurred
                response.sendRedirect("error.jsp");
            }
        } else {
            // Authentication failed, redirect to login page
            response.sendRedirect("login.jsp");
        }
    }
}
