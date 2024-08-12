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

import inventory.InventoryItemDAOImpl;

/**
 * Servlet implementation class PurchaseItemServlet
 * This servlet handles the purchase of an item from the inventory.
 */
@WebServlet("/PurchaseItemServlet")
public class PurchaseItemServlet extends HttpServlet {
    private final InventoryItemDAOImpl inventoryDAO = new InventoryItemDAOImpl();

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
        // Get the itemId parameter from the request
        int itemId = Integer.parseInt(request.getParameter("itemId"));

        // Remove the inventory item with the given itemId from the database
        inventoryDAO.deleteInventoryItem(itemId);

        // Redirect the user back to the consumer inventory list
        response.sendRedirect(request.getContextPath() + "/ConsumerServlet");
    }
}
