package tranlong5252.foodsupplychain.utils;

import java.sql.PreparedStatement;

public interface PreparedStatementFunction<R> {
    R apply(PreparedStatement statement) throws Exception;
}
