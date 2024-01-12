package tranlong5252.foodsupplychain.database.dao;

import tranlong5252.foodsupplychain.database.DataAccess;
import tranlong5252.foodsupplychain.model.ClientCompany;
import tranlong5252.foodsupplychain.model.Product;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ProductDao extends DataAccess<Product> {

    static ProductDao getInstance() {
        try {
            return DataAccess.getInstance(ProductDao.class);
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    List<Product> getByCompany(ClientCompany company);
    List<Product> getByCompany(ClientCompany company, int page);

    void addProductToCompany(Product product, ClientCompany company);
}
