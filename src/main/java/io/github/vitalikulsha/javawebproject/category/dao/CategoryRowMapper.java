package io.github.vitalikulsha.javawebproject.category.dao;

import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.category.entity.Category;
import io.github.vitalikulsha.javawebproject.util.dao.rowmapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category getEntity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(Column.CATEGORY_ID.name());
        String name = resultSet.getString(Column.NAME.name());
        return new Category(id, name);
    }
}
