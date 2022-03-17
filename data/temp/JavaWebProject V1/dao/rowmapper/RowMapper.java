package io.github.vitalikulsha.JavaWebProject.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    T getEntity(ResultSet resultSet) throws SQLException;
}
