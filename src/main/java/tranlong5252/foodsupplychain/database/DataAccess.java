package tranlong5252.foodsupplychain.database;

import tranlong5252.foodsupplychain.database.connections.DatabaseConnection;
import tranlong5252.foodsupplychain.utils.Logger;
import tranlong5252.foodsupplychain.utils.PreparedStatementFunction;
import tranlong5252.foodsupplychain.utils.ResultSetAction;
import tranlong5252.foodsupplychain.utils.ResultSetFunction;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DataAccess<K> {

    Map<Class<DataAccess>, DataAccess> instanceList = new HashMap<>();

    static <T extends DataAccess> T getInstance(Class<T> type) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        DataAccess instance = instanceList.get(type);
        if (instance == null) {
            var basePackage = "tranlong5252.foodsupplychain.database.dao.impl";
            var dbType = DatabaseConnection.getInstance().getTypeName();
            var daoClass = Class.forName(basePackage + "." + dbType + "." + type.getSimpleName() + "Impl");
            instance = type.cast(daoClass.getDeclaredConstructor().newInstance());
            instanceList.put((Class<DataAccess>) type, instance);
        }
        return type.cast(instance);
    }

    /**
     * Initialize the PreparedStatement with the given SQL statement
     */
    default <R> R statement(String str, PreparedStatementFunction<R> action) {
        PreparedStatement statement = null;
        try {
            statement = DatabaseConnection.getInstance().getConnection().prepareStatement(str);
            return action.apply(statement);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }/* finally {
			cleanup(null, statement);
		}*/
    }

    /**
     * Initialize the PreparedStatement with the given SQL statement, with the option to return generated keys
     */
    default <R> R statementWithKey(String str, PreparedStatementFunction<R> action) {
        PreparedStatement statement = null;
        try {
            statement = DatabaseConnection.getInstance().getConnection().prepareStatement(str, Statement.RETURN_GENERATED_KEYS);
            return action.apply(statement);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }/* finally {
			cleanup(null, statement);
		}*/
    }

    /**
     * Process the ResultSet
     */
    default <R> R fetch(PreparedStatement statement, ResultSetFunction<R> action) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return action.apply(resultSet);
            }
            return null;
        } catch (Exception ex) {
            Logger.severe(ex, "fetch error");
            ex.printStackTrace();
            return null;
        }/* finally {
			cleanup(resultSet, statement);
        }*/
    }

    /**
     * Iterate through all records in the ResultSet
     */
    default <R> void fetchIterate(PreparedStatement statement, ResultSetAction<R> action) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                action.execute(resultSet);
            }
        } catch (Exception ex) {
            Logger.severe(ex, "fetch error");
        }/* finally {
			cleanup(resultSet, statement);
		}*/
    }

    /**
     * Map all records in the ResultSet to a list
     */
    default <R> List<R> fetchRecords(PreparedStatement statement, ResultSetAction<R> action) {
        List<R> records = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                R record = action.execute(resultSet);
                records.add(record);
            }
        } catch (Exception ex) {
            Logger.severe(ex, "fetch error");
            ex.printStackTrace();
        }/* finally {
			cleanup(resultSet, statement);
		}*/
        return records;
    }

    default void cleanup(ResultSet result, Statement statement) {
        if (result != null) {
            try {
                result.close();
            } catch (SQLException ex) {
                Logger.severe(ex, "SQLException on cleanup");
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.severe(ex, "SQLException on cleanup");
            }
        }
    }

    K get(int id);

    List<K> getList();

    int update(K obj);

    void delete(K obj);
}