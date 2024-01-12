package tranlong5252.foodsupplychain.database.dao.impl.mysql;

import tranlong5252.foodsupplychain.database.dao.ProductDao;
import tranlong5252.foodsupplychain.model.ClientCompany;
import tranlong5252.foodsupplychain.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private Product newProduct(int id, String name, double price, int quantity) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        return product;
    }

    @Override
    public Product get(int id) {
        String stm = "SELECT * FROM product WHERE id = ?";
        return statement(stm, statement -> {
            statement.setInt(1, id);
            return fetch(statement, resultSet -> newProduct(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("quantity")
            ));
        });
    }

    @Override
    public List<Product> getList() {
        String stm = "SELECT * FROM product";
        return statement(stm, statement -> fetchRecords(statement, resultSet -> newProduct(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getDouble("price"),
                resultSet.getInt("quantity")
        )));
    }

    @Override
    public List<Product> getListByPage(int page) {
        int from = (page - 1) * 10;
        int to = page * 10;
        String stm = "SELECT * FROM product LIMIT ?, ?";
        return statement(stm, statement -> {
            statement.setInt(1, from);
            statement.setInt(2, to);
            return fetchRecords(statement, resultSet -> newProduct(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("quantity")
            ));
        });
    }

    @Override
    public int update(Product obj) {
        if (obj.getId() == 0) {
            String stm = "INSERT INTO product (name, price, quantity) VALUES (?, ?, ?)";
            return statementWithKey(stm, statement -> {
                statement.setString(1, obj.getName());
                statement.setDouble(2, obj.getPrice());
                statement.setInt(3, obj.getQuantity());
                statement.executeUpdate();
                ResultSet generatedKeysResultSet = statement.getGeneratedKeys();
                generatedKeysResultSet.next();
                return generatedKeysResultSet.getInt(1);
            });
        } else {
            String stm = "UPDATE product SET name = ?, price = ?, quantity = ? WHERE id = ?";
            statement(stm, statement -> {
                statement.setString(1, obj.getName());
                statement.setDouble(2, obj.getPrice());
                statement.setInt(3, obj.getQuantity());
                statement.setInt(4, obj.getId());
                return statement.executeUpdate();
            });
            return obj.getId();
        }
    }

    @Override
    public void delete(Product obj) throws SQLException {
        String stm = "DELETE FROM product WHERE id = ?";
        statement(stm, statement -> {
            statement.setInt(1, obj.getId());
            return statement.executeUpdate();
        });
    }

    @Override
    public int count() {
        String stm = "SELECT COUNT(*) FROM product";
        return statement(stm, statement -> fetch(statement, resultSet -> resultSet.getInt(1)));
    }

    @Override
    public int countSearch(String name) {
        String stm = "SELECT COUNT(*) FROM product WHERE name LIKE CONCAT('%', ?, '%')";
        return statement(stm, statement -> {
            statement.setString(1, name);
            return fetch(statement, resultSet -> resultSet.getInt(1));
        });
    }

    @Override
    public List<Product> getByCompany(ClientCompany company) {
        String stm = "SELECT * FROM product WHERE id IN (SELECT product_id FROM product_company WHERE company_id = ?)";
        return statement(stm, statement -> {
            statement.setInt(1, company.getId());
            return fetchRecords(statement, resultSet -> newProduct(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("quantity")
            ));
        });
    }

    @Override
    public List<Product> getByCompany(ClientCompany company, int page) {
        int from = (page - 1) * 10;
        int to = page * 10;
        String stm = "SELECT * FROM product WHERE id IN (SELECT product_id FROM product_company WHERE company_id = ?) LIMIT ?, ?";
        return statement(stm, statement -> {
            statement.setInt(1, company.getId());
            statement.setInt(2, from);
            statement.setInt(3, to);
            return fetchRecords(statement, resultSet -> newProduct(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("quantity")
            ));
        });
    }

    @Override
    public void addProductToCompany(Product product, ClientCompany company) {
        String stm = "INSERT INTO product_company (product_id, company_id) VALUES (?, ?) ON DUPLICATE KEY UPDATE product_id = product_id";
        statement(stm, statement -> {
            statement.setInt(1, product.getId());
            statement.setInt(2, company.getId());
            return statement.executeUpdate();
        });
    }
}
