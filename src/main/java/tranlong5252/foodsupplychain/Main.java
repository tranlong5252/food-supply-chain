package tranlong5252.foodsupplychain;

import tranlong5252.foodsupplychain.database.connections.DatabaseConnection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Main implements ServletContextListener {
    private static Main instance;

    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseConnection.init("mysql");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DatabaseConnection.unload();
    }
}
