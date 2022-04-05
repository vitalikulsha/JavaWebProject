package io.github.vitalikulsha.javawebproject.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Row mapper interface
 *
 * @param <T> entity type in this row mapper
 */
public interface RowMapper<T> {
    /**
     * Builds entity from result set
     *
     * @param resultSet query result set
     * @return entity type
     * @throws SQLException throw SQL exception
     */
    T getEntity(ResultSet resultSet) throws SQLException;
}
