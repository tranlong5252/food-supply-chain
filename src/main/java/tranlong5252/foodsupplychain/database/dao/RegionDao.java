package tranlong5252.foodsupplychain.database.dao;

import tranlong5252.foodsupplychain.database.DataAccess;
import tranlong5252.foodsupplychain.model.Region;

import java.lang.reflect.InvocationTargetException;

public interface RegionDao extends DataAccess<Region> {

    static RegionDao getInstance() {
        try {
            return DataAccess.getInstance(RegionDao.class);
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}