package Servlet;
/**
 *
 * @author Yuyang Du, Chang Li
 */
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import inventory.InventoryItem;
import inventory.InventoryItemDAO;
import inventory.InventoryItemDAOImpl;
import java.util.List;
import subscription.Emailer;
import subscription.SubscriptionDAO;
import subscription.SubscriptionDAOImpl;
import subscription.subscription;

/**
 * Servlet implementation class AddNewItemServlet
 * This servlet handles the addition of a new item to the inventory.
 */
@WebServlet("/AddNewItemServlet")
public class AddNewItemServlet extends HttpServlet {
    private final InventoryItemDAO inventoryDAO = new InventoryItemDAOImpl();
    private final SubscriptionDAO subsDAO = new SubscriptionDAOImpl();
    private final Emailer mail = new Emailer();
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
        // Retrieve parameters from the form submission
        String itemName = request.getParameter("itemName");
        String itemDescription = request.getParameter("itemDescription");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date expirationDate = Date.valueOf(request.getParameter("expirationDate"));
        boolean forDonation = request.getParameter("forDonation") != null;
        boolean surplus = request.getParameter("surplus") != null;

        // Create a new InventoryItem object
        InventoryItem newItem = new InventoryItem();
        newItem.setItemName(itemName);
        newItem.setItemDescription(itemDescription);
        newItem.setQuantity(quantity);
        newItem.setExpirationDate(expirationDate);
        newItem.setForDonation(forDonation);
        newItem.setSurplus(surplus);
        newItem.setStoreId((int) request.getSession().getAttribute("userId"));

        // Add the new item to the inventory
        inventoryDAO.addInventoryItem(newItem);
        //grab the list of every email subscripbed to this store
        List<subscription> subs = subsDAO.getAllSubscribers((int) request.getSession().getAttribute("userId"));
        for(int i=0; i<subs.size() ; i++){
            //send a email to each subscriber
            mail.send(subs.get(i).getUserEmail(), (String) request.getSession().getAttribute("SenderEmail"));
        }
        // Redirect back to the inventory management page
        response.sendRedirect(request.getContextPath() + "/RetailerServlet");
    }
}
