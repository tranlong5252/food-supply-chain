package tranlong5252.foodsupplychain.database.dao.impl.mysql;

import tranlong5252.foodsupplychain.database.dao.AccountDao;
import tranlong5252.foodsupplychain.model.Account;

import java.sql.ResultSet;
import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private Account newAccount(int id, String username, String password) {
        Account account = new Account();
        account.setId(id);
        account.setUsername(username);
        account.setPassword(password);
        return account;
    }

    @Override
    public Account get(int id) {
        return statement("SELECT * FROM account WHERE id = ?", statement -> {
            statement.setInt(1, id);
            return fetch(statement, resultSet -> newAccount(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password")
            ));
        });
    }

    @Override
    public List<Account> getList() {
        return statement("SELECT * FROM account", statement -> fetchRecords(statement, resultSet -> newAccount(
                resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("password")
        )));
    }

    @Override
    public List<Account> getListByPage(int page) {
        //1 page = 50 entries
        int from = (page - 1) * 50;
        int to = page * 50;
        return statement("SELECT * FROM account LIMIT ?, ?", statement -> {
            statement.setInt(1, from);
            statement.setInt(2, to);
            return fetchRecords(statement, resultSet -> newAccount(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password")
            ));
        });
    }

    @Override
    public int update(Account obj) {
        if (obj.getId() != 0) {
            statement("UPDATE account SET username = ?, password = ? WHERE id = ?", statement -> {
                statement.setString(1, obj.getUsername());
                statement.setString(2, obj.getPassword());
                statement.setInt(3, obj.getId());
                return statement.executeUpdate();
            });
            return obj.getId();
        }
        return statementWithKey("INSERT INTO account (username, password) VALUES (?, ?)", statement -> {
            statement.setString(1, obj.getUsername());
            statement.setString(2, obj.getPassword());
            statement.executeUpdate();
            ResultSet generatedKeysResultSet = statement.getGeneratedKeys();
            generatedKeysResultSet.next();
            return generatedKeysResultSet.getInt(1);
        });
    }

    @Override
    public void delete(Account obj) {
        statement("DELETE FROM account WHERE id = ?", statement -> {
            statement.setInt(1, obj.getId());
            statement.executeUpdate();
            return null;
        });
    }

    @Override
    public Account login(String username, String password) {
        return statement("SELECT * FROM account WHERE username = ? AND password = ?", statement -> {
            statement.setString(1, username);
            statement.setString(2, password);
            return fetch(statement, resultSet -> newAccount(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password")
            ));
        });
    }
}
