package DAOInterface;

import Entity.Feedback;

public interface FeedbackDAO {
    void addFeedback(Feedback var1);
    void updateFeedback(int var1, int var2, String var3);
    void deleteFeedback(int var1);
    void replyToFeedback(int var1, String var2);
    void viewAllFeedback();
    Feedback getFeedbackById(int var1);
}
