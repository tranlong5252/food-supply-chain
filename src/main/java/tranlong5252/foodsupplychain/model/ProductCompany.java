package tranlong5252.foodsupplychain.model;

import tranlong5252.foodsupplychain.database.dao.ProductDao;

import java.util.ArrayList;
import java.util.List;

public class ProductCompany {
    private ClientCompany clientCompany;
    private List<Product> products;

    public ProductCompany(ClientCompany clientCompany) {
        this.clientCompany = clientCompany;
        this.products = ProductDao.getInstance().getByCompany(clientCompany);
    }

    public ProductCompany() {
        this.products = new ArrayList<>();
    }

    public ClientCompany getClientCompany() {
        return clientCompany;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
}
