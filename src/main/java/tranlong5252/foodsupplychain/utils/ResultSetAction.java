package tranlong5252.foodsupplychain.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetAction<R> {
    R execute(ResultSet resultSet) throws SQLException;
}
