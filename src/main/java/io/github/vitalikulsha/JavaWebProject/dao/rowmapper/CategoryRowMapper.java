package io.github.vitalikulsha.JavaWebProject.dao.rowmapper;

import io.github.vitalikulsha.JavaWebProject.dao.constant.Column;
import io.github.vitalikulsha.JavaWebProject.entity.Category;

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
