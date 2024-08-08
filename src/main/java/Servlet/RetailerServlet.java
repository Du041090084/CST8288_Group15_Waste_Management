package Servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import inventory.InventoryItem;
import inventory.InventoryItemDAO;
import inventory.InventoryItemDAOImpl;

/**
 * Servlet implementation class RetailerServlet
 * This servlet handles requests related to retailers.
 */
@WebServlet("/RetailerServlet")
public class RetailerServlet extends HttpServlet {
    private InventoryItemDAO inventoryDAO = new InventoryItemDAOImpl();

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
        // Fetch inventory items for the retailer from the database
        List<InventoryItem> inventoryItems = inventoryDAO.getAllInventoryItems();

        // Set inventoryItems as an attribute in the request
        request.setAttribute("inventoryItems", inventoryItems);

        // Forward the request to retailer.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("retailer.jsp");
        dispatcher.forward(request, response);
    }
}
