package tranlong5252.foodsupplychain.database.connections.types;

import tranlong5252.foodsupplychain.database.connections.DatabaseConnection;
import tranlong5252.foodsupplychain.utils.Logger;

import java.sql.DriverManager;

public class MySqlDBC extends DatabaseConnection {

    public MySqlDBC(String host, int port, String database, String user, String password) {
        try {
            Logger.info("Connecting to the database (MySQL)...");

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
            Logger.info("Connected to the database (MySQL)");
        } catch (Exception ex) {
            Logger.severe(ex, "Could not connect to the database");
        }
    }

    public String getTypeName() {
        return "mysql";
    }
}
