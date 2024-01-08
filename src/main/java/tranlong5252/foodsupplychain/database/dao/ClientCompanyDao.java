package tranlong5252.foodsupplychain.database.dao;

import tranlong5252.foodsupplychain.database.DataAccess;
import tranlong5252.foodsupplychain.model.Account;
import tranlong5252.foodsupplychain.model.ClientCompany;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ClientCompanyDao extends DataAccess<ClientCompany> {

    static ClientCompanyDao getInstance() {
        try {
            return DataAccess.getInstance(ClientCompanyDao.class);
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    List<ClientCompany> search(String name, int page);

    ClientCompany getByUser(Account account);
}
