package tranlong5252.foodsupplychain.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetFunction <R> {
    R apply(ResultSet resultSet) throws SQLException;
}
