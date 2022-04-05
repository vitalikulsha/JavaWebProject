package io.github.vitalikulsha.javawebproject.dao.rowmapper;

import io.github.vitalikulsha.javawebproject.dao.query.constant.Column;
import io.github.vitalikulsha.javawebproject.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category getEntity(ResultSet resultSet) throws SQLException {
        try {
            int id = resultSet.getInt(Column.CATEGORY_ID.name());
            String name = resultSet.getString(Column.NAME.name());
            return new Category(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
