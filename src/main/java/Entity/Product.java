package Entity;

public class Product {
    private int Product_id;
    private String Product_name;
    private String Category;

    public Product(int product_id, String category, String product_name) {
        this.Product_id = product_id;
        this.Category = category;
        this.Product_name = product_name;
    }

    public int getProduct_id() {
        return this.Product_id;
    }

    public void setProduct_id(int product_id) {
        this.Product_id = product_id;
    }

    public String getProduct_name() {
        return this.Product_name;
    }

    public void setProduct_name(String product_name) {
        this.Product_name = product_name;
    }

    public String getCategory() {
        return this.Category;
    }

    public void setCategory(String category) {
        this.Category = category;
    }
}
