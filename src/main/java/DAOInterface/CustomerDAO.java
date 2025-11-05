package DAOInterface;

import Entity.Customer;

public interface CustomerDAO {
    void addCustomer(Customer var1);

    void updateCustomerEmail(int var1, String var2);

    void deleteCustomer(int var1);

    Customer getCustomerById(int var1);

    Customer getCustomerByEmail(String var1);
}
