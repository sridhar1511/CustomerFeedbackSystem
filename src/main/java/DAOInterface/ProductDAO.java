package DAOInterface;

import Entity.Product;

public interface ProductDAO {
    void addProduct(Product var1);

    void updateProductCategory(int var1, String var2);

    void deleteProduct(int var1);

    Product getProductById(int var1);
}
