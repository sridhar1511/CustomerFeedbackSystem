package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import DB.DBConnection;
import Entity.Product;
import Execption.DAOExecption;
import Util.LoggerUtil;
public class ProductDAO implements DAOInterface.ProductDAO {
    private static final Logger log = LoggerUtil.getLogger();
    private final Connection con = DBConnection.getConnection();

    @Override
    public void addProduct(Product p) throws DAOExecption {
        String query = "INSERT INTO Product (Product_name, Category) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, p.getProduct_name());
            ps.setString(2, p.getCategory());
            ps.executeUpdate();
            log.info("Product added: " + p.getProduct_name());
        } catch (SQLException e) {
            log.severe("Error adding product: " + e.getMessage());
            throw new DAOExecption("Error adding product", e);
        }
    }

    @Override
    public void updateProductCategory(int productId, String newCategory) throws DAOExecption {
        String query = "UPDATE Product SET Category = ? WHERE Product_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, newCategory);
            ps.setInt(2, productId);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Product updated." : "Product not found.");
            log.info("Product category updated: " + newCategory);
        } catch (SQLException e) {
            log.severe("Error updating product: " + e.getMessage());
            throw new DAOExecption("Error updating product", e);
        }
    }

    @Override
    public void deleteProduct(int productId) throws DAOExecption {
        String query = "DELETE FROM Product WHERE Product_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, productId);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Product deleted." : "Product not found.");
            log.info("Product deleted: " + productId);
        } catch (SQLException e) {
            log.severe("Error deleting product: " + e.getMessage());
            throw new DAOExecption("Error deleting product", e);
        }
    }

    @Override
    public Product getProductById(int id) throws DAOExecption {
        String query = "SELECT * FROM Product WHERE Product_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    log.info("Product found: " + id);
                    return new Product(
                            rs.getInt("Product_id"),
                            rs.getString("Product_name"),
                            rs.getString("Category")
                    );
                } else {
                    log.info("Product not found: " + id);
                    return null;
                }
            }
        } catch (SQLException e) {
            log.severe("Error retrieving product: " + e.getMessage());
            throw new DAOExecption("Error retrieving product", e);
        }
    }
}
