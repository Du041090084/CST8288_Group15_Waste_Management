package Servlet;
/**
 *
 * @author Chang Li
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.User;
import user.UserDAO;
import user.UserDAOImpl;
import user.UserType;

/**
 * Servlet implementation class RegisterServlet
 * This servlet handles user registration.
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAOImpl();

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
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userTypeString = request.getParameter("userType");
        String contactInfo = request.getParameter("contactInfo");
        String location = request.getParameter("location");

        // Convert user type string to enum
        UserType userType = UserType.valueOf(userTypeString.toUpperCase());

        // Create a new user object
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserType(userType);
        user.setContactInfo(contactInfo);
        user.setLocation(location);

        // Call DAO method to create the user
        userDAO.create(user);

        // Redirect to the login page
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
