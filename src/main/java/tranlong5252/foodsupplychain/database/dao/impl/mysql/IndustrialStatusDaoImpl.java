package tranlong5252.foodsupplychain.database.dao.impl.mysql;

import tranlong5252.foodsupplychain.constants.StatusLevel;
import tranlong5252.foodsupplychain.database.dao.IndustrialStatusDao;
import tranlong5252.foodsupplychain.model.IndustrialAgriculturalStatus;

import java.sql.ResultSet;
import java.util.List;

public class IndustrialStatusDaoImpl implements IndustrialStatusDao {

    private IndustrialAgriculturalStatus newStatus(
            int id,
            String name,
            StatusLevel level,
            double value,
            int potential,
            int development
    ) {
        IndustrialAgriculturalStatus status = new IndustrialAgriculturalStatus();
        status.setId(id);
        status.setName(name);
        status.setLevel(level);
        status.setValue(value);
        status.setPotential(potential);
        status.setDevelopment(development);
        return status;
    }


    @Override
    public IndustrialAgriculturalStatus get(int id) {
        return statement("SELECT * FROM status WHERE id = ?", statement -> {
            statement.setInt(1, id);
            return fetch(statement, resultSet ->
                    newStatus(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            StatusLevel.getByValue(resultSet.getInt("level")),
                            resultSet.getDouble("value"),
                            resultSet.getInt("potential"),
                            resultSet.getInt("development")
                    )
            );
        });
    }

    @Override
    public List<IndustrialAgriculturalStatus> getList() {
        return statement("SELECT * FROM status", statement ->
                fetchRecords(statement, resultSet ->
                        newStatus(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                StatusLevel.getByValue(resultSet.getInt("level")),
                                resultSet.getDouble("value"),
                                resultSet.getInt("potential"),
                                resultSet.getInt("development")
                        )
                )
        );
    }

    @Override
    public List<IndustrialAgriculturalStatus> getListByPage(int page) {
        //1 page = 50 entries
        int from = (page - 1) * 50;
        int to = page * 50;
        return statement("SELECT * FROM status LIMIT ?, ?", statement -> {
            statement.setInt(1, from);
            statement.setInt(2, to);
            return fetchRecords(statement, resultSet ->
                    newStatus(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            StatusLevel.getByValue(resultSet.getInt("level")),
                            resultSet.getDouble("value"),
                            resultSet.getInt("potential"),
                            resultSet.getInt("development")
                    )
            );
        });
    }

    @Override
    public int update(IndustrialAgriculturalStatus obj) {
        if (obj.getId() != 0) {
            statement("UPDATE status SET name = ?, level = ?, value = ?, potential = ?, development = ? WHERE id = ?", statement -> {
                statement.setString(1, obj.getName());
                statement.setInt(2, obj.getLevel().getValue());
                statement.setDouble(3, obj.getValue());
                statement.setInt(4, obj.getPotential());
                statement.setInt(5, obj.getDevelopment());
                statement.setInt(6, obj.getId());
                return statement.executeUpdate();
            });
            return obj.getId();
        }
        return statementWithKey("INSERT INTO status (name, level, value, potential, development) VALUES (?, ?, ?, ?, ?)", statement -> {
            statement.setString(1, obj.getName());
            statement.setInt(2, obj.getLevel().getValue());
            statement.setDouble(3, obj.getValue());
            statement.setInt(4, obj.getPotential());
            statement.setInt(5, obj.getDevelopment());
            statement.executeUpdate();
            ResultSet generatedKeysResultSet = statement.getGeneratedKeys();
            generatedKeysResultSet.next();
            return generatedKeysResultSet.getInt(1);
        });
    }

    @Override
    public void delete(IndustrialAgriculturalStatus obj) {
        statement("DELETE FROM region_status WHERE status_id = ?", statement -> {
            statement.setInt(1, obj.getId());
            statement.executeUpdate();
            return null;
        });
        statement("DELETE FROM status WHERE id = ?", statement -> {
            statement.setInt(1, obj.getId());
            statement.executeUpdate();
            return null;
        });
    }
}
