package tranlong5252.foodsupplychain.database.dao;

import tranlong5252.foodsupplychain.database.DataAccess;
import tranlong5252.foodsupplychain.model.IndustrialAgriculturalStatus;

import java.lang.reflect.InvocationTargetException;

public interface IndustrialStatusDao extends DataAccess<IndustrialAgriculturalStatus> {

    static IndustrialStatusDao getInstance() {
        try {
            return DataAccess.getInstance(IndustrialStatusDao.class);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
