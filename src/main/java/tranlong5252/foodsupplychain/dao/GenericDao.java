package tranlong5252.foodsupplychain.dao;

import tranlong5252.foodsupplychain.utils.mapper.RowMapper;

import java.util.List;

public interface GenericDao<T> {

    <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);

    void update(String sql, Object... parameters);

    Long insert(String sql, Object... parameters);

    int count(String sql, Object... parameters);
}
