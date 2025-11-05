package Entity;

public class Customer {
    private int Customer_ID;
    private String Customer_Name;
    private String Customer_EMail;

    public Customer(int customerId, String Customer_Name, String Customer_EMail) {
        this.Customer_ID = this.Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Customer_EMail = Customer_EMail;
    }

    public Customer(String Customer_Name, String Customer_EMail) {
        this.Customer_Name = Customer_Name;
        this.Customer_EMail = Customer_EMail;
    }

    public int getCustomer_ID() {
        return this.Customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        this.Customer_ID = customer_ID;
    }

    public String getCustomer_EMail() {
        return this.Customer_EMail;
    }

    public void setCustomer_EMail(String customer_EMail) {
        this.Customer_EMail = customer_EMail;
    }

    public String getCustomer_Name() {
        return this.Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        this.Customer_Name = customer_Name;
    }

}
