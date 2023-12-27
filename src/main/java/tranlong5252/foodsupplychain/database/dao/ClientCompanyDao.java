package tranlong5252.foodsupplychain.database.dao;

import tranlong5252.foodsupplychain.database.DataAccess;
import tranlong5252.foodsupplychain.model.ClientCompany;

import java.lang.reflect.InvocationTargetException;

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

    ClientCompany search(String name);
}
