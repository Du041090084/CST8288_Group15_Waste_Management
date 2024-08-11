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
 * Servlet implementation class ConsumerServlet
 * This servlet handles requests related to consumers.
 */
@WebServlet("/ConsumerServlet")
public class ConsumerServlet extends HttpServlet {
    private final InventoryItemDAOImpl inventoryDAO = new InventoryItemDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<InventoryItem> inventoryItems = inventoryDAO.getAllInventoryItems();
        
        // Log retrieved items
        System.out.println("Retrieved inventory items: " + inventoryItems.size());

        request.setAttribute("inventoryItems", inventoryItems);
        RequestDispatcher dispatcher = request.getRequestDispatcher("consumer.jsp");
        dispatcher.forward(request, response);
    }
}
