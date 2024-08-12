package Servlet;
/**
 *
 * @author Yuyang Du, Chang Li
 */
import inventory.InventoryItem;
import inventory.InventoryItemDAOImpl;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import subscription.Emailer;
import subscription.SubscriptionDAO;
import subscription.SubscriptionDAOImpl;
import subscription.subscription;

/**
 * Servlet implementation class EditItemServlet
 * This servlet handles requests related to editing inventory items.
 */
@WebServlet("/EditItemServlet")
public class EditItemServlet extends HttpServlet {
    private InventoryItemDAOImpl inventoryDAO = new InventoryItemDAOImpl();
    private final SubscriptionDAO subsDAO = new SubscriptionDAOImpl();
    private final Emailer mail = new Emailer();
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
        inventoryDAO.deleteInventoryItem(itemId);
        response.sendRedirect("retailer.jsp");
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

        InventoryItem updatedItem = new InventoryItem(itemId, itemName, itemDescription, quantity, expirationDate, forDonation, surplus,(int) request.getSession().getAttribute("userId"));

        inventoryDAO.updateInventoryItem(updatedItem);
        //if item is marked surplus
        if (surplus){
            //grap a lsit of each email subscribed to this store
            List<subscription> subs = subsDAO.getAllSubscribers((int) request.getSession().getAttribute("userId"));
            for(int i=0; i<subs.size() ; i++){
                //send a email to each address
                mail.send(subs.get(i).getUserEmail(), (String) request.getSession().getAttribute("SenderEmail"));
            }
        }
        response.sendRedirect(request.getContextPath() + "/RetailerServlet");
    }
}

}
