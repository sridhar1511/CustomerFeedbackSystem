package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import DB.DBConnection;
import Entity.Feedback;
import Execption.DAOExecption;
import Util.LoggerUtil;
public class FeedbackDAO implements DAOInterface.FeedbackDAO{
    private static final Logger log = LoggerUtil.getLogger();
    private final Connection con = DBConnection.getConnection();

    @Override
    public void addFeedback(Feedback f) {
        String query = "INSERT INTO Feedback (Customer_ID, Product_id, Rating, Comments) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, f.getCustomer_ID());
            ps.setInt(2, f.getProduct_id());
            ps.setInt(3, f.getRating());
            ps.setString(4, f.getComments());
            ps.executeUpdate();
            log.info("Feedback added successfully.");
        } catch (SQLException e) {
            log.severe("Error adding feedback: " + e.getMessage());
            throw new DAOExecption("Error while adding feedback", e);
        }
    }

    @Override
    public void updateFeedback(int feedbackId, int newRating, String newComment) {
        String query = "UPDATE Feedback SET Rating = ?, Comments = ? WHERE Feedback_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, newRating);
            ps.setString(2, newComment);
            ps.setInt(3, feedbackId);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Feedback updated." : "Feedback not found.");
            log.info("Feedback updated successfully.");
        } catch (SQLException e) {
            log.severe("Error updating feedback: " + e.getMessage());
            throw new DAOExecption("Error while updating feedback", e);
        }
    }

    @Override
    public void deleteFeedback(int id) {
        String query = "DELETE FROM Feedback WHERE Feedback_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Feedback deleted." : "Feedback not found.");
            log.info("Feedback deleted successfully.");
        } catch (SQLException e) {
            log.severe("Error deleting feedback: " + e.getMessage());
            throw new DAOExecption("Error while deleting feedback", e);
        }
    }

    @Override
    public void replyToFeedback(int id, String replyMsg) {
        String query = "UPDATE Feedback SET Replay = ? WHERE Feedback_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, replyMsg);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Reply added." : "Feedback not found.");
            log.info("Reply added successfully.");
        } catch (SQLException e) {
            log.severe("Error replying to feedback: " + e.getMessage());
            throw new DAOExecption("Error while replying to feedback", e);
        }
    }

    @Override
    public void viewAllFeedback() {
        String query = """
            SELECT f.Feedback_id, c.Customer_Name AS Customer, p.Product_name AS Product,
                   f.Rating, f.Comments, COALESCE(f.Replay, '-') AS Replay
            FROM Feedback f
            JOIN Customer c ON f.Customer_ID = c.Customer_ID
            JOIN Product p ON f.Product_id = p.Product_id
        """;
        try (PreparedStatement st = con.prepareStatement(query);
             ResultSet rs = st.executeQuery(query)) {

            System.out.println("\nID | Customer | Product | Rating | Comments | Replay");
            System.out.println("-----------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%d | %s | %s | %d | %s | %s%n",
                        rs.getInt("Feedback_id"),
                        rs.getString("Customer"),
                        rs.getString("Product"),
                        rs.getInt("Rating"),
                        rs.getString("Comments"),
                        rs.getString("Replay"));
            }
            log.info("Feedback viewed successfully.");
        } catch (SQLException e) {
            log.severe("Error viewing feedback: " + e.getMessage());
            throw new DAOExecption("Error while viewing feedback", e);
        }
    }

    @Override
    public Feedback getFeedbackById(int id) {
        String query = "SELECT * FROM Feedback WHERE Feedback_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Feedback(
                            rs.getInt("Feedback_id"),
                            rs.getInt("Customer_ID"),
                            rs.getInt("Product_id"),
                            rs.getInt("Rating"),
                            rs.getString("Comments"),
                            rs.getString("Replay")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            log.severe("Error retrieving feedback: " + e.getMessage());
            throw new DAOExecption("Error while retrieving feedback", e);
        }
    }
}
