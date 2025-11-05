package Entity;

public class Feedback {
    private int Feedback_id;
    private int Customer_ID;
    private int Product_id;
    private int Rating;
    private String Comments;
    private String Replay;

    public Feedback(int Customer_ID, int Product_id, int Rating, String Comments) {
        this.Customer_ID = Customer_ID;
        this.Product_id = Product_id;
        this.Rating = Rating;
        this.Comments = Comments;
    }

    public Feedback(int Feedback_id, int Rating, String Comments, String Replay) {
        this.Feedback_id = Feedback_id;
        this.Rating = Rating;
        this.Comments = Comments;
        this.Replay = Replay;
    }
    public Feedback(int Feedback_id, int Customer_ID, int Product_id,
                    int Rating, String Comments, String Replay) {
        this.Feedback_id = Feedback_id;
        this.Customer_ID = Customer_ID;
        this.Product_id = Product_id;
        this.Rating = Rating;
        this.Comments = Comments;
        this.Replay = Replay;
    }

    public int getFeedback_id() {
        return this.Feedback_id;
    }

    public int getCustomer_ID() {
        return this.Customer_ID;
    }

    public int getProduct_id() {
        return this.Product_id;
    }

    public int getRating() {
        return this.Rating;
    }

    public String getComments() {
        return this.Comments;
    }

    public String getReplay() {
        return this.Replay;
    }

    public void setFeedback_id(int feedback_id) {
        Feedback_id = feedback_id;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    public void setProduct_id(int product_id) {
        Product_id = product_id;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public void setReplay(String replay) {
        Replay = replay;
    }
}
