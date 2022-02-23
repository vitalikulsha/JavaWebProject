package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.config.ConnectionSource;
import io.github.vitalikulsha.JavaWebProject.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private final ConnectionSource connectionSource = ConnectionSource.instance();

    @Override
    public Category getById(Integer id) {
        String sqlQuery = "SELECT * FROM category WHERE category_id=?";
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getCategory(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        String sqlQuery = "SELECT * FROM category";
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                categories.add(getCategory(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public void delete(Category category) {

    }

    private Category getCategory(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("category_id");
            String name = resultSet.getString("name");
            return new Category(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
