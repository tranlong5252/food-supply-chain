package tranlong5252.foodsupplychain.database.connections;

import tranlong5252.foodsupplychain.database.connections.types.MySqlDBC;
import tranlong5252.foodsupplychain.utils.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DatabaseConnection {
    private static DatabaseConnection instance;
    protected Connection connection;

    public static DatabaseConnection getInstance() {
        return instance;
    }

    public static void init(String dbType) {
        switch (dbType) {
            case "mysql":
                var host = "172.25.127.215";
                var port = 3306;
                var database = "food_supply_chain";
                var user = "admin";
                var password = "123456";
                if (dbType.equals("mysql")) {
                    instance = new MySqlDBC(host, port, database, user, password);
                }
                break;
            case "mongodb":
                throw new UnsupportedOperationException("mongodb is not supported yet");
            default:
                throw new UnsupportedOperationException("invalid database type");
        }
    }

    public static void unload() {
        if (instance != null) {
            instance.disconnect();
        }
    }

    private void disconnect() {
        Logger.info("Disconnecting from the " + getTypeName() + " database...");
        try {
            if (connection != null) connection.close();
        } catch (SQLException ex) {
            Logger.severe(ex, "Could not disconnect from the database");
        }
    }

    public abstract String getTypeName();

    public Connection getConnection() {
        return connection;
    }
}
