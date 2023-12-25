package tranlong5252.foodsupplychain.dao.impl.region;

import tranlong5252.foodsupplychain.dao.impl.AbstractDao;
import tranlong5252.foodsupplychain.model.Region;
import tranlong5252.foodsupplychain.model.RegionList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegionDao extends AbstractDao<Region> {

    private static RegionList regions;

    public static RegionList getListRegions() {
        if (regions == null) {
            regions = new RegionList();
        }
        return regions;
    }

    private Region getRegion(String regionName) {
        Region region = new Region();
        region.setName(regionName);

        String query = "SELECT * FROM regions WHERE name = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, regionName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                region.setName(resultSet.getString("name"));
                region.setStatuses(new IndustrialStatusDao().getIndustrialStatuses(regionName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return region;
    }
}