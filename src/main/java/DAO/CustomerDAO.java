package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import DB.DBConnection;
import Entity.Customer;
import Execption.DAOExecption;
import Util.LoggerUtil;
public class CustomerDAO implements DAOInterface.CustomerDAO {
    private static final Logger log = LoggerUtil.getLogger();
    private final Connection con = DBConnection.getConnection();

    @Override
    public void addCustomer(Customer c) {
        String query = "INSERT INTO Customer (Customer_Name, Customer_Email) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, c.getCustomer_Name());
            ps.setString(2, c.getCustomer_EMail());
            ps.executeUpdate();
            log.info("Customer added: " + c.getCustomer_Name());
        } catch (SQLException e) {
            log.severe("Error adding customer: " + e.getMessage());
            throw new DAOExecption("Failed to add customer", e);
        }
    }

    @Override
    public void updateCustomerEmail(int id, String newEmail) {
        String query = "UPDATE Customer SET Customer_Email = ? WHERE Customer_ID = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, newEmail);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Customer updated." : "Customer not found.");
            log.info("Customer email updated for ID: " + id);
        } catch (SQLException e) {
            log.severe("Error updating customer: " + e.getMessage());
            throw new DAOExecption("Failed to update customer", e);
        }
    }

    @Override
    public void deleteCustomer(int id) {
        String query = "DELETE FROM Customer WHERE Customer_ID = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Customer deleted." : "Customer not found.");
            log.info("Customer deleted: " + id);
        } catch (SQLException e) {
            log.severe("Error deleting customer: " + e.getMessage());
            throw new DAOExecption("Failed to delete customer", e);
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        String query = "SELECT * FROM Customer WHERE Customer_ID = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer c = new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Customer_Email")
                );
                log.info("Customer found: " + c.getCustomer_Name());
                return c;
            } else {
                log.info("Customer not found for ID: " + id);
                return null;
            }
        } catch (SQLException e) {
            log.severe("Error retrieving customer: " + e.getMessage());
            throw new DAOExecption("Failed to get customer by ID", e);
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        String query = "SELECT * FROM Customer WHERE Customer_Email = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer c = new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Customer_Email")
                );
                log.info("Customer found: " + c.getCustomer_Name());
                return c;
            } else {
                log.info("Customer not found for email: " + email);
                return null;
            }
        } catch (SQLException e) {
            log.severe("Error fetching customer by email: " + e.getMessage());
            throw new DAOExecption("Failed to get customer by email", e);
        }
    }

}
