package tranlong5252.foodsupplychain.database.dao;

import tranlong5252.foodsupplychain.database.DataAccess;
import tranlong5252.foodsupplychain.model.Account;

import java.lang.reflect.InvocationTargetException;

public interface AccountDao extends DataAccess<Account> {

    static AccountDao getInstance() {
        try {
            return DataAccess.getInstance(AccountDao.class);
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    Account login(String username, String password);
}
