package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.domain.Author;
import io.github.vitalikulsha.JavaWebProject.util.ConnectionSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorDaoImpl implements AuthorDao {
    private final ConnectionSource connectionSource = ConnectionSource.instance();

    @Override
    public Optional<Author> getById(Integer id) {
        String sqlQuery = "SELECT * FROM author WHERE id=?";
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(getAuthor(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Author> getAll() {
        List<Author> categories = new ArrayList<>();
        String sqlQuery = "SELECT * FROM author";
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                categories.add(getAuthor(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Author save(Author author) {
        return null;
    }

    @Override
    public void delete(Author author) {

    }

    private Author getAuthor(ResultSet resultSet) {
        try {
            Integer id = resultSet.getInt("author_id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            return new Author(id, firstName, lastName);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
