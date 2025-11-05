import DAO.CustomerDAO;
import DAO.FeedbackDAO;
import DAO.ProductDAO;
import Entity.Customer;
import Entity.Feedback;
import Entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DAOTestCase {
    @Test
    public void testAddCustomer() {
        CustomerDAO dao = new CustomerDAO();
        String uniqueEmail = "test" + System.currentTimeMillis() + "@example.com";
        Customer c = new Customer(0, "TestUser", uniqueEmail);
        dao.addCustomer(c);
        Customer result = dao.getCustomerByEmail(uniqueEmail);
        Assertions.assertNotNull(result, "Customer should be found in DB after insertion");
    }

    @Test
    public void testAddProduct() {
        ProductDAO dao = new ProductDAO();
        Product p = new Product(0, "Electronics", "Laptop");
        dao.addProduct(p);
        Product result = dao.getProductById(1);
        Assertions.assertNotNull(result, "Product should be found in DB after insertion");
    }

    @Test
    public void testAddFeedback() {
        FeedbackDAO dao = new FeedbackDAO();
        Feedback f = new Feedback(1, 1, 5, "Excellent product!");
        dao.addFeedback(f);
        Feedback result = dao.getFeedbackById(1);
        Assertions.assertNotNull(result, "Feedback should be found in DB after insertion");
    }

    @Test
    public void testReplyToFeedback() {
        FeedbackDAO dao = new FeedbackDAO();
        dao.replyToFeedback(1, "Thank you for your feedback!");
        Feedback result = dao.getFeedbackById(1);
        Assertions.assertEquals("Thank you for your feedback!", result.getReplay());
    }

//    @Test
//    void testUpdateProductCategory() {
//        ProductDAO dao = new ProductDAO();
//        dao.updateProductCategory(1, "Electronics");
//        Product updated = dao.getProductById(1);
//        Assertions.assertEquals("Electronics", updated.getCategory());
//    }

    @Test
    void testDeleteProduct() {
        ProductDAO dao = new ProductDAO();
        dao.deleteProduct(0);
        Product deleted = dao.getProductById(0);
        Assertions.assertNull(deleted);
    }

    @Test
    void testUpdateCustomerEmail() {
        CustomerDAO dao = new CustomerDAO();
        dao.updateCustomerEmail(1, "newmail@example.com");
        Customer updated = dao.getCustomerById(1);
        Assertions.assertEquals("newmail@example.com", updated.getCustomer_EMail());
    }

    @Test
    void testDeleteCustomer() {
        CustomerDAO dao = new CustomerDAO();
        dao.deleteCustomer(3);
        Customer deleted = dao.getCustomerById(3);
        Assertions.assertNull(deleted);
    }
}
