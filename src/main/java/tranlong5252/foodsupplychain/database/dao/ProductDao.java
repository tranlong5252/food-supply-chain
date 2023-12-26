package tranlong5252.foodsupplychain.database.dao;

import tranlong5252.foodsupplychain.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public static List<Product> getListProducts() {
        List<Product> products = new ArrayList<>();
        Product p1 = new Product();
        p1.setId(1);
        p1.setName("Hello");
        p1.setPrice(5.2);
        products.add(p1);

        Product p2 = new Product();
        p2.setId(2);
        p2.setName("From");
        p2.setPrice(2.12);
        products.add(p2);

        Product p3 = new Product();
        p3.setId(3);
        p3.setName("Server");
        p3.setPrice(23.12);
        products.add(p3);
        return products;
    }

    public static Product getProduct(int id) {
        List<Product> products = getListProducts();
        Product product = null;
        for (Product p : products) {
            if (p.getId() == id) {
                product = p;
                break;
            }
        }
        return product;
    }
}
