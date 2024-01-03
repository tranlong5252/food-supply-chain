package tranlong5252.foodsupplychain.database.dao;

import tranlong5252.foodsupplychain.database.DataAccess;
import tranlong5252.foodsupplychain.model.Region;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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

    List<Region> search(String name, int page);
}