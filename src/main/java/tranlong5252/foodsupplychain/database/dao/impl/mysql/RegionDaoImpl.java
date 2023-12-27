package tranlong5252.foodsupplychain.database.dao.impl.mysql;

import tranlong5252.foodsupplychain.constants.StatusLevel;
import tranlong5252.foodsupplychain.database.dao.RegionDao;
import tranlong5252.foodsupplychain.model.IndustrialAgriculturalStatus;
import tranlong5252.foodsupplychain.model.NatureStatus;
import tranlong5252.foodsupplychain.model.Population;
import tranlong5252.foodsupplychain.model.Region;
import tranlong5252.foodsupplychain.model.StatusList;

import java.sql.ResultSet;
import java.util.List;

public class RegionDaoImpl implements RegionDao {

    private Region newRegion(int id, String name, Population population, NatureStatus natureStatus) {
        Region region = new Region();
        region.setId(id);
        region.setName(name);
        region.setPopulation(population);
        region.setNatureStatus(natureStatus);
        return region;
    }

    @Override
    public List<Region> getList() {
        return statement("SELECT * FROM region", statement -> fetchRecords(statement, resultSet -> {
            Population population = new Population();
            population.setDistribution(resultSet.getInt("distribution"));
            population.setMigration(resultSet.getInt("migration"));
            population.setUrbanization(resultSet.getInt("urbanization"));
            NatureStatus natureStatus = new NatureStatus();
            natureStatus.setAgricultureLand(resultSet.getInt("agriculture_land"));
            natureStatus.setForestLand(resultSet.getInt("forest_land"));
            natureStatus.setDisaster(resultSet.getString("disaster"));

            int id = resultSet.getInt("id");
            List<IndustrialAgriculturalStatus> list = getRegionStatuses(id);
            Region region = newRegion(id, resultSet.getString("name"), population, natureStatus);
            region.setStatuses(new StatusList(list));
            return region;
        }));

    }

    private StatusList getRegionStatuses(int id) {
        var list = fetchRecords(
                statement("SELECT * FROM region_status INNER JOIN status ON region_status.status_id = status.id WHERE region_status.region_id = ?"
                        , statement -> {
                    statement.setInt(1, id);
                    return statement;
                }), resultSet -> {
                        IndustrialAgriculturalStatus status = new IndustrialAgriculturalStatus();
                        status.setId(resultSet.getInt("id"));
                        status.setName(resultSet.getString("name"));
                        status.setLevel(StatusLevel.getByValue(resultSet.getInt("level")));
                        status.setValue(resultSet.getDouble("value"));
                        status.setPotential(resultSet.getInt("potential"));
                        status.setDevelopment(resultSet.getInt("development"));
                        return status;
                    });
        return new StatusList(list);
    }

    @Override
    public int add(Region obj) {
        return statementWithKey("INSERT INTO region (name, distribution, migration, urbanization, agriculture_land, forest_land, disaster) VALUES (?, ?, ?, ?, ?, ?, ?)", statement -> {
            statement.setString(1, obj.getName());
            statement.setDouble(2, obj.getPopulation().getDistribution());
            statement.setInt(3, obj.getPopulation().getMigration());
            statement.setInt(4, obj.getPopulation().getUrbanization());
            statement.setDouble(5, obj.getNatureStatus().getAgricultureLand());
            statement.setDouble(6, obj.getNatureStatus().getForestLand());
            statement.setString(7, obj.getNatureStatus().getDisaster());
            statement.executeUpdate();
            ResultSet generatedKeysResultSet = statement.getGeneratedKeys();
            generatedKeysResultSet.next();
            return generatedKeysResultSet.getInt(1);
        });
    }

    @Override
    public Region get(int id) {
        Region region = statement("SELECT * FROM region WHERE id = ?", statement -> {
            statement.setInt(1, id);
            return fetch(statement, resultSet -> {
                Population population = new Population();
                population.setDistribution(resultSet.getInt("distribution"));
                population.setMigration(resultSet.getInt("migration"));
                population.setUrbanization(resultSet.getInt("urbanization"));
                NatureStatus natureStatus = new NatureStatus();
                natureStatus.setAgricultureLand(resultSet.getInt("agriculture_land"));
                natureStatus.setForestLand(resultSet.getInt("forest_land"));
                natureStatus.setDisaster(resultSet.getString("disaster"));
                return newRegion(resultSet.getInt("id"), resultSet.getString("name"), population, natureStatus);
            });
        });
        if (region != null) {
            StatusList list = getRegionStatuses(id);
            region.setStatuses(list);
        }
        return region;
    }

    @Override
    public void update(Region obj) {
        statement("UPDATE region SET name = ?, distribution = ?, migration = ?, urbanization = ?, agriculture_land = ?, forest_land = ?, disaster = ? WHERE id = ?", statement -> {
            statement.setString(1, obj.getName());
            statement.setDouble(2, obj.getPopulation().getDistribution());
            statement.setInt(3, obj.getPopulation().getMigration());
            statement.setInt(4, obj.getPopulation().getUrbanization());
            statement.setDouble(5, obj.getNatureStatus().getAgricultureLand());
            statement.setDouble(6, obj.getNatureStatus().getForestLand());
            statement.setString(7, obj.getNatureStatus().getDisaster());
            statement.setInt(8, obj.getId());
            statement.executeUpdate();
            return null;
        });
        for (int i = 0; i < obj.getStatuses().size(); i++) {
            int finalI = i;
            statement("INSERT INTO region_status (region_id, status_id) VALUES (?, ?) ON DUPLICATE KEY UPDATE region_id = region_id", statement -> {
                statement.setInt(1, obj.getId());
                statement.setInt(2, obj.getStatuses().get(finalI).getId());
                statement.executeUpdate();
                return null;
            });
        }
    }

    @Override
    public void delete(Region obj) {
        statement("DELETE FROM region WHERE id = ?", statement -> {
            statement.setInt(1, obj.getId());
            statement.executeUpdate();
            return null;
        });
    }

    @Override
    public List<Region> search(String name) {
        return statement("SELECT * FROM region WHERE name LIKE CONCAT( '%',?,'%')" , statement -> {
            statement.setString(1, name);
            List<Region> region = fetchRecords(statement, resultSet -> {
                Population population = new Population();
                population.setDistribution(resultSet.getInt("distribution"));
                population.setMigration(resultSet.getInt("migration"));
                population.setUrbanization(resultSet.getInt("urbanization"));
                NatureStatus natureStatus = new NatureStatus();
                natureStatus.setAgricultureLand(resultSet.getInt("agriculture_land"));
                natureStatus.setForestLand(resultSet.getInt("forest_land"));
                natureStatus.setDisaster(resultSet.getString("disaster"));
                return newRegion(resultSet.getInt("id"), resultSet.getString("name"), population, natureStatus);
            });
            if (region != null) {
                region.forEach(region1 -> region1.setStatuses(getRegionStatuses(region1.getId())));
            }
            return region;
        });
    }
}