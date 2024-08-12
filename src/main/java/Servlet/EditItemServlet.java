package Servlet;

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

/**
 * Servlet implementation class EditItemServlet
 * This servlet handles requests related to editing inventory items.
 */
@WebServlet("/EditItemServlet")
public class EditItemServlet extends HttpServlet {
    private InventoryItemDAOImpl inventoryDAO = new InventoryItemDAOImpl();

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
        int itemId = Integer.parseInt(request.getParameter("itemId"));

        // Retrieve the inventory item from the database using its itemId
        InventoryItem item = inventoryDAO.getInventoryItemById(itemId);

        // Set the inventory item as an attribute in the request
        request.setAttribute("item", item);

        // Forward the request to the edit-item.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("editItem.jsp");
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
    String itemIdStr = request.getParameter("itemId");
    if (itemIdStr == null || itemIdStr.isEmpty()) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid item ID");
        return;
    }

    int itemId;
    try {
        itemId = Integer.parseInt(itemIdStr);
    } catch (NumberFormatException e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid item ID format");
        return;
    }

    if (request.getParameter("delete") != null) {
        inventoryDAO.deleteInventoryItem(itemId);
        response.sendRedirect(request.getContextPath() + "/RetailerServlet");
    } else {
        String itemName = request.getParameter("itemName");
        String itemDescription = request.getParameter("itemDescription");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date expirationDate = Date.valueOf(request.getParameter("expirationDate"));
        boolean forDonation = request.getParameter("forDonation") != null;
        boolean surplus = request.getParameter("surplus") != null;

        InventoryItem updatedItem = new InventoryItem(itemId, itemName, itemDescription, quantity, expirationDate, forDonation, surplus);

        inventoryDAO.updateInventoryItem(updatedItem);

        response.sendRedirect(request.getContextPath() + "/RetailerServlet");
    }
}

}
