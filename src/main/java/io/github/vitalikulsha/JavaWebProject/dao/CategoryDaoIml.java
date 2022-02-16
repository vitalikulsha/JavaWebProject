package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.util.ConnectionSource;
import io.github.vitalikulsha.JavaWebProject.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDaoIml implements CategoryDao {
    private final ConnectionSource connectionSource = ConnectionSource.instance();

    @Override
    public Optional<Category> getById(Long Id) {
        return Optional.empty();
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        String sqlQuery = "SELECT id, name FROM category";
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                categories.add(new Category(id, name));
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
}
