package Servlet;
/**
 *
 * @author Yuyang Du, Chang Li
 */
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inventory.InventoryItem;
import inventory.InventoryItemDAOImpl;

/**
 * Servlet implementation class CharitableOrganizationServlet
 * This servlet handles requests related to charitable organizations.
 */
@WebServlet("/CharitableOrganizationServlet")
public class CharitableOrganizationServlet extends HttpServlet {
    private final InventoryItemDAOImpl inventoryDAO = new InventoryItemDAOImpl();

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
        // Fetch donation inventory items from the database
        List<InventoryItem> donationInventoryItems = inventoryDAO.getDonationInventoryItems();

        // Set donationInventoryItems as an attribute in the request
        request.setAttribute("inventoryItems", donationInventoryItems);

        // Forward the request to charitable_organization.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("charitable_organization.jsp");
        dispatcher.forward(request, response);
    }
}
