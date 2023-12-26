package tranlong5252.foodsupplychain.database.dao;

import tranlong5252.foodsupplychain.model.Account;

public class AccountDao {
    public static Account login(String username, String password) {
        Account account = null;
        if ("long123".equals(username) && "123".equals(password)) {
            account = new Account();
            account.setId(1);
            account.setUsername("long123");
            account.setPassword("123");
        }
        return account;
    }

    public static Account getById(int id) {
        Account account = null;
        if (id == 1) {
            account = new Account();
            account.setId(1);
            account.setUsername("long123");
            account.setPassword("123");
        }
        return account;
    }
}
